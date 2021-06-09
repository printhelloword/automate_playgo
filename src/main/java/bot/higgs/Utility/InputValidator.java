package bot.higgs.Utility;

import bot.higgs.CodMobileApplication;

public class InputValidator {

    public static boolean isInputNumeric(String input) {
        boolean status = false;
        try {
            Long.parseLong(input);
            status = true;
        } catch (Exception e) {
            CodMobileApplication.logger.info("Input Not Numeric");
        }
        return status;
    }
}
