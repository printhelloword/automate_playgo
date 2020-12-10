package bot.playgo.Utility;

import bot.playgo.PlaygoApplication;

public class InputValidator {

    public static boolean isInputNumeric(String input) {
        boolean status = false;
        try {
            Long.parseLong(input);
            status = true;
        } catch (Exception e) {
            PlaygoApplication.logger.info("Input Not Numeric");
        }
        return status;
    }
}
