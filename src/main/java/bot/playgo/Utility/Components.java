package bot.playgo.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Components {

    public static Date formattedRequestTimestamp;
    public static final String dateFormatPattern = "yyyy-MM-dd hh:mm:ss";
    public static final String stringFalse = "-1";

    private static String msisdn;

    @Value("${bot.msisdn}")
    private void setMsisdn(String value) {
        this.msisdn = value;
    }

    private static String terminalId;

    @Value("${bot.terminal.id}")
    private void setTerminalId(String value) {
        this.terminalId = value;
    }

    private static String otpWaitTime;

    @Value("${bot.wait.otp}")
    private void setOtpWaitTime(String value) {
        this.otpWaitTime = value;
    }

    private static String otpMaxRetry;

    @Value("${bot.retry.otp}")
    private void setOtpMaxRetry(String value) {
        this.otpMaxRetry = value;
    }

    @Autowired
    public Components() {

    }

    public static String getMsisdn() {
        return msisdn;
    }

    public static String getTerminalId() {
        return terminalId;
    }

    public static String getOtpWaitTime() {
        return otpWaitTime;
    }

    public static String getOtpMaxRetry() {
        return otpMaxRetry;
    }

    public static Map<String, String> getDenominations() {
        return getDenominationsIdMap();
    }

    static Map<String, String> getDenominationsIdMap() {
        Map<String, String> denominationMap = new HashMap<>();

        denominationMap.put("12", "477");
        denominationMap.put("28", "478");
        denominationMap.put("59", "479");
        denominationMap.put("85", "480");
        denominationMap.put("170", "481");
        denominationMap.put("296", "482");
        denominationMap.put("875", "483");
        denominationMap.put("starlight", "484");
        denominationMap.put("starlight193", "485");
        denominationMap.put("starlight586", "486");
        denominationMap.put("starlightplus", "487");

        return denominationMap;
    }

    public static Map<String, String> getSubDenominations() {
        return getSubDenominationsIdMap();
    }

    static Map<String, String> getSubDenominationsIdMap() {
        Map<String, String> subDenominationsMap = new HashMap<>();

        // denomination_id, bonus_id
        subDenominationsMap.put("718", "22");
        subDenominationsMap.put("719", "22");
        subDenominationsMap.put("720", "23");
        subDenominationsMap.put("721", "24");
        subDenominationsMap.put("722", "25");
        subDenominationsMap.put("723", "26");
        subDenominationsMap.put("724", "26");

        return subDenominationsMap;
    }

}
