package bot.playgo.Utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Components {

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

    public static String getOtpWaitTime(){
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

        denominationMap.put("5", "716");
        denominationMap.put("12", "717");
        denominationMap.put("50", "718");
        denominationMap.put("70", "719");
        denominationMap.put("140", "720");
        denominationMap.put("355", "721");
        denominationMap.put("720", "722");
        denominationMap.put("1450", "723");
        denominationMap.put("2180", "724");

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
        subDenominationsMap.put("7248", "26");

        return subDenominationsMap;
    }

}
