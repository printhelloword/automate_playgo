package bot.playgo.Utility;

import bot.playgo.MlbbApplication;

public class InputValidator {

    public static boolean isInputNumeric(String input) {
        boolean status = false;
        try {
            Long.parseLong(input);
            status = true;
        } catch (Exception e) {
            MlbbApplication.logger.info("Input Not Numeric");
        }
        return status;
    }
}
