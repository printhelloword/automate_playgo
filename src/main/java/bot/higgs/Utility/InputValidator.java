package bot.higgs.Utility;

import bot.higgs.HiggsDominoApplication;

public class InputValidator {

    public static boolean isInputNumeric(String input) {
        boolean status = false;
        try {
            Long.parseLong(input);
            status = true;
        } catch (Exception e) {
            HiggsDominoApplication.logger.info("Input Not Numeric");
        }
        return status;
    }
}
