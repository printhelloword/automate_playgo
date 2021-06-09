package bot.higgs.Controller;

import bot.higgs.Entity.*;
import bot.higgs.Model.Inbox;
import bot.higgs.Model.Outbox;
import bot.higgs.CodMobileApplication;
import bot.higgs.Pojo.ResponsePojo;
import bot.higgs.Pojo.VoucherPojo;
import bot.higgs.Utility.Components;
import bot.higgs.Utility.DBUtilInboxes;
import bot.higgs.Utility.DBUtilOutboxes;
import bot.higgs.Utility.InputValidator;
import org.json.JSONObject;

import java.util.Map;

public class TransactionController {

    private static final String STATUS_FAILED = "Failed";
    private static final String STATUS_SUCCESS = "Success";
    private static final String TAG_STATUS_DEFAULT = STATUS_FAILED;

    private static final String MSG_ERROR_TRX_PLAYER_ID = "TrxID/PlayerID Tidak Valid";
    private static final String MSG_ERROR_TRX_ID_EXISTS = "TrxID Sudah Terdapat Di Database";
    private static final String MSG_ERROR_DENOMINATION_INVALID = "Denom Tidak Valid";

    private static final Map<String, String> denominationsMap = Components.getDenominations();
    private static final Map<String, String> subDenominationsMap = Components.getSubDenominations();

    private Integer inboxId;
    private ResponsePojo responsePojo;
    private VoucherPojo voucherPojo;

    private final Request request;

    private String getRequesParams() {
        return request.getPlayerId() + "/" + request.getDenom() + "/" + request.getTrxId();
    }

    public TransactionController(Request request) {
        this.request = request;
    }

    public static TransactionController ofRequest(Request request) {
        return new TransactionController(request);
    }

    public ResponsePojo getResponsePojo() {
        initResponsePojo();
        initVoucherPojo();

        RequestValidity requestVAlidity = checkRequestValidity();

        if (!requestVAlidity.isStatus()){
            updateResponseMessage(requestVAlidity.getMessage());
        }else{
            saveToInbox(createNewInbox());
            checkInboxisSavedAndStartTransaction();
        }

        printResponseJsonAndSaveToOutbox(new JSONObject(responsePojo));
        return responsePojo;
    }

    private RequestValidity checkRequestValidity() {
        boolean status = false;
        String message="";

        if (!areTrxIdAndPlayerIdValid()) {
            updateMessage();
            message =  MSG_ERROR_TRX_PLAYER_ID;
        } else {
            if (isTrxIdAlreadyExists())
                message = MSG_ERROR_TRX_ID_EXISTS;
            else {
                if (!isDenominationValid())
                    message = MSG_ERROR_DENOMINATION_INVALID;
                else {
                    status=true;
                }
            }
        }

        return new RequestValidity(status, message);
    }

    private void updateMessage() {
    }

    private void initResponsePojo() {
        responsePojo = ResponsePojo.byWithTrxIdStatusMessage(request.getTrxId(), TAG_STATUS_DEFAULT, "");
    }

    private void initVoucherPojo() {
        voucherPojo = VoucherPojo.byPlayerIdAndDenom(request.getPlayerId(), request.getDenom());
    }

    private void updateResponseMessage(String message) {
        responsePojo.setMessage(message);
    }

    private void checkInboxisSavedAndStartTransaction() {
        if (isSaveToInboxSucceed()) {
            Voucher voucher = createNewVoucher();
            CodMobile codMobile = CodMobile.ofVoucher(voucher);

            Map<Boolean, String> transactionResult = codMobile.processTopTupAndGetMessage();

            if (transactionResult != null) {
                responsePojo.setVoucher(voucherPojo);
                for (Map.Entry<Boolean, String> entry : transactionResult.entrySet()) {
                    responsePojo.setStatus((entry.getKey()) ? STATUS_SUCCESS : STATUS_FAILED);
                    responsePojo.setMessage(entry.getValue());
                }
            }
        }
    }

    private Voucher createNewVoucher() {
        Voucher voucher = Voucher.withoutBonus(voucherPojo.getPlayerId(), denominationsMap.get(voucherPojo.getDenom()));
        if (isSubDenominationExists(voucher.getDenomId()))
            voucher.setDenomBonusId(subDenominationsMap.get(voucher.getDenomId()));
        return voucher;
    }

    private void printResponseJsonAndSaveToOutbox(JSONObject jsonObject) {
        CodMobileApplication.logger.info("Returning JSON : ");
        CodMobileApplication.logger.info(jsonObject.toString(4));
        if (inboxId != null)
            saveToOutbox(createNewBoutbox());
    }

    private Inbox createNewInbox() {
        String message = request.getTrxId() + "#" + request.getDenom() + "#" + request.getPlayerId();
        return new Inbox(message, getRequesParams(), 0, getJavaUtilDate(), request.getTrxId());
    }

    private Outbox createNewBoutbox() {
        return new Outbox(new JSONObject(responsePojo).toString(), null, getJavaUtilDate(), inboxId);
    }

    private void saveToOutbox(Outbox newBoutbox) {
        try {
            DBUtilOutboxes.saveOutbox(newBoutbox);
        } catch (Exception e) {
            CodMobileApplication.logger.info(e.getMessage());
            CodMobileApplication.logger.info("Failed Save To Outbox");
        }
    }

    private void saveToInbox(Inbox newInbox) {
        try {
            inboxId = DBUtilInboxes.saveInbox(newInbox);
        } catch (Exception e) {
            CodMobileApplication.logger.info(e.getMessage());
            CodMobileApplication.logger.info("Failed Save To DB");
        }
    }

    private boolean isSaveToInboxSucceed() {
        return inboxId != null;
    }

    private boolean areTrxIdAndPlayerIdValid() {
        return (InputValidator.isInputNumeric(responsePojo.getTrxId()) && InputValidator.isInputNumeric(voucherPojo.getPlayerId()));
    }

    private boolean isTrxIdAlreadyExists() {
        return DBUtilInboxes.isTrxIdExists(request.getTrxId());
    }

    private boolean isDenominationValid() {
        return denominationsMap.containsKey(request.getDenom());
    }

    private boolean isSubDenominationExists(String denomId) {
        return subDenominationsMap.containsKey(denomId);
    }

    private java.util.Date getJavaUtilDate() {
        return new java.util.Date();
    }

}
