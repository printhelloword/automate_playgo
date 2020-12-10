package bot.playgo.Entity;

import bot.playgo.PlaygoApplication;
import bot.playgo.Pojo.Request.*;
import bot.playgo.Pojo.Response.CreateResponse;
import bot.playgo.Pojo.Response.NormalizeResponse;
import bot.playgo.Pojo.Response.TopUpResponse;
import bot.playgo.Pojo.Response.ValidateResponse;
import bot.playgo.Utility.Components;
import bot.playgo.Utility.DBUtilOtp;
import bot.playgo.Utility.OkHttpClientCertificateManager;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;

public class PlayGo {

    private static String msisdn = Components.getMsisdn();
    private static String otpWaitTime = Components.getOtpWaitTime();
    private static String otpMaxRetry = Components.getOtpMaxRetry();

    private final OkHttpClient client = getHttpClient();
    private boolean status;

    private OkHttpClient getHttpClient() {
        return OkHttpClientCertificateManager.getUnsafeOkHttpClient();
    }

    private static final String URL_BASE = "https://playgo.co.id/";
    private static final String URI_VALIDATION = "api/topup/user/validate/";
    private static final String URI_NORMALIZE = "tapi/normalize/";
    private static final String URI_CREATE = "api/products/otp/create";
    private static final String URI_TOPUP = "api/topup/order/create/";
    private static final String DEFAULT_PRODUCT_ID_FREE_FIRE = "8";
    private static final String DEFAULT_OPERATOR_ID = "669";

    private static final String RESULT_CODE_PIN_GENERATED = "PIN_GENERATED";
    private static final String RESULT_CODE_PIN_INVALID = "INVALID_PIN";
    private static final String RESULT_CODE_PIN_BLOCKED = "BLOCKED_PIN";
    private static final String RESULT_CODE_SUCCESS = "SUCCESS";
    private static final String RESULT_CODE_INVALID_PLAYER = "PLAYER NOT FOUND";
    private static final String RESULT_CODE_PHONE_NUMBER_NOT_XL = "CHECK XL NUMBER";
    private static final String RESULT_CODE_400 = "BAD REQUEST <400>";
    private static final String RESULT_CODE_200 = "REQUEST OK <200>";

    private final String BOOLEAN_FALSE = "false";
    private final String BOOLEAN_TRUE = "true";

    private final String KEY_VALIDATION_TOKEN = "validationToken";

    private String OTP;

    private final Map<Boolean, String> transactionStatus = new HashMap<>();

    private static Voucher voucher;
    String message = "";
    private ValidateResponse validateResponse;
    private NormalizeResponse normalizeResponse;
    private CreateResponse createResponse;
    private TopUpResponse topUpResponse;

    public static PlayGo ofVoucher(Voucher voucher) {
        return new PlayGo(voucher);
    }

    public PlayGo(Voucher voucher) {
        this.voucher = voucher;
    }

    public Map<Boolean, String> processTopTupAndGetMessage() {
        try {
            PlaygoApplication.logger.info(">>>> START BOT <<<<");
            processTransaction();
        } catch (Exception e) {
            PlaygoApplication.logger.info(e.getMessage());
        }
        udpdateTransactionResult();

        return transactionStatus;
    }

    private void processTransaction() throws Exception {
        processValidate();
        processNormalize();
        processCreate();

        waitForOtp();
        //wait for interval of time for updated OTP

        getOtpAndProcessTopUp();

        retryTopUpForBlockedOrInvalidPin();
    }

    private void retryTopUpForBlockedOrInvalidPin() throws Exception {
        for (int i = 0; i < Integer.parseInt(otpMaxRetry); i++) {
            if (isTopUpSucced())
                break;
            else if (isPinInvalid()) {
                waitForOtp();
                PlaygoApplication.logger.info("Retry OTP. Count=" + (i + 1)+"x");
                getOtpAndProcessTopUp();
            }
        }
    }

    private boolean isPinInvalid() throws Exception {
        return topUpResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_PIN_INVALID) || topUpResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_PIN_BLOCKED);
    }

    private void waitForOtp() throws Exception {
        sleep(Integer.parseInt(otpWaitTime));
    }

    private void sleep(int timeInSecond) throws Exception {
        Thread.sleep(timeInSecond * 1000);
    }

    private okhttp3.Request makeValidateRequest() throws Exception {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String json = new Gson().toJson(getValidateRequestObject());
        RequestBody body = RequestBody.create(JSON, json);
        printRequest(URI_VALIDATION, json);

        return new okhttp3.Request.Builder().post(body).url(URL_BASE + URI_VALIDATION).build();
    }

    private okhttp3.Request makeNormalizeRequest() throws Exception {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String json = new Gson().toJson(getNormalizeRequestObject());
        printRequest(URI_NORMALIZE, json);
        RequestBody body = RequestBody.create(JSON, json);

        return new okhttp3.Request.Builder().post(body).url(URL_BASE + URI_NORMALIZE).build();
    }

    private okhttp3.Request makeCreateRequest() throws Exception {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String json = new Gson().toJson(getCreateRequestObject());
        printRequest(URI_CREATE, json);
        RequestBody body = RequestBody.create(JSON, json);

        return new okhttp3.Request.Builder().post(body).url(URL_BASE + URI_CREATE).build();
    }

    private okhttp3.Request makeTopUpRequest() throws Exception {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        String json = new Gson().toJson(getTopUpRequestObject());
        RequestBody body = RequestBody.create(JSON, json);
        printRequest(URI_TOPUP, json);

        return new okhttp3.Request.Builder().post(body).url(URL_BASE + URI_TOPUP).build();
    }

    private ValidateRequest getValidateRequestObject() {
        Fields fields = Fields.ofUserId(voucher.getPlayerId());
        return ValidateRequest.ofFieldsAndProductId(fields, DEFAULT_PRODUCT_ID_FREE_FIRE);
    }

    private NormalizeRequest getNormalizeRequestObject() {
        return NormalizeRequest.ofMsisdnAndOperatorId(msisdn, DEFAULT_OPERATOR_ID);
    }

    private CreateRequest getCreateRequestObject() throws Exception {
        CreateRequest createRequest = CreateRequest.withoutBonus(DEFAULT_PRODUCT_ID_FREE_FIRE, msisdn, voucher.getDenomId(), validateResponse.getValidationToken());
        if (voucher.getDenomBonusId() != null)
            createRequest.setBonusId(voucher.getDenomBonusId());
        return createRequest;
    }

    private TopUpRequest getTopUpRequestObject() throws Exception {
        TopUpRequest topUpRequest = TopUpRequest.withoutBonus(validateResponse.getValidationToken(), DEFAULT_PRODUCT_ID_FREE_FIRE, OTP, voucher.getDenomId(), msisdn);
        if (voucher.getDenomBonusId() != null)
            topUpRequest.setBonusId(voucher.getDenomBonusId());
        return topUpRequest;
    }

    private String executeRequest(okhttp3.Request request) throws Exception {
        Response response = client.newCall(request).execute();
        if (response.code() == 400) {
            PlaygoApplication.logger.info(RESULT_CODE_400);
        } else {
            PlaygoApplication.logger.info(RESULT_CODE_200);
        }
        return response.body().string();
    }

    private void udpdateTransactionResult() {
        PlaygoApplication.logger.info("Updating transaction Result");

        if (!isValidated()) {
            updateMessage(RESULT_CODE_INVALID_PLAYER);
        } else {
            if (!isCreated()) {
                updateMessage(RESULT_CODE_PHONE_NUMBER_NOT_XL);
            } else {
                if (topUpResponse != null) {
                    status = isTopUpSucced();
                    updateMessage(getTopUpResponseMessage());
                    if (status) {
                        updateMessage(message + " - " + validateResponse.getUsername());
                    }
                }
            }
        }

        transactionStatus.put(status, message);
    }

    private void updateMessage(String message) {
        this.message = message;
    }

    private boolean isCreated() {
        return createResponse != null;
    }

    private String getTopUpResponseMessage() {
        return topUpResponse.getResultCode();
    }

    private boolean isTopUpSucced() {
        return (topUpResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_SUCCESS));
    }

    private void processOTP() throws Exception {
        if (isOTPGenerated()) {
            OTP = DBUtilOtp.getOTP();
        }
    }

    private boolean isOTPGenerated() {
        return (createResponse.getResultCode().equalsIgnoreCase(RESULT_CODE_PIN_GENERATED));
    }

    private void printRequest(String requestName, String json) {
        PlaygoApplication.logger.info("--> Request :" + requestName + ", Body -> " + json);
    }

    private void printResponse(String validateResponse) {
        PlaygoApplication.logger.info("<-- Response : " + validateResponse);
    }

    private void processValidate() throws Exception {
        okhttp3.Request request = makeValidateRequest();
        String jsonString = executeRequest(request);
        printResponse(jsonString);
        validateResponse = new Gson().fromJson(jsonString, ValidateResponse.class);
    }

    private void processNormalize() throws Exception {
        okhttp3.Request request = makeNormalizeRequest();
        String jsonString = executeRequest(request);
        printResponse(jsonString);
        normalizeResponse = new Gson().fromJson(jsonString, NormalizeResponse.class);
    }

    private boolean isValidated() {
        return (validateResponse != null);
    }

    private void processCreate() throws Exception {
        okhttp3.Request request = makeCreateRequest();
        String jsonString = executeRequest(request);
        printResponse(jsonString);
        createResponse = new Gson().fromJson(jsonString, CreateResponse.class);
    }

    private void processTopUp() throws Exception {
        okhttp3.Request request = makeTopUpRequest();
        String jsonString = executeRequest(request);
        printResponse(jsonString);
        topUpResponse = new Gson().fromJson(jsonString, TopUpResponse.class);
    }

    private void getOtpAndProcessTopUp() throws Exception {
        processOTP();
        processTopUp();
    }


}
