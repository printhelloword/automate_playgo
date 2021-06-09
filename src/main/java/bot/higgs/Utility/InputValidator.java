package bot.higgs.Utility;

import bot.higgs.AovApplication;

public class InputValidator {

    public static boolean isInputNumeric(String input) {
        boolean status = false;
        try {
            Long.parseLong(input);
            status = true;
        } catch (Exception e) {
            AovApplication.logger.info("Input Not Numeric");
        }
        return status;
    }
}
