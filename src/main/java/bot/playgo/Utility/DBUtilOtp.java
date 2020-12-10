package bot.playgo.Utility;

import bot.playgo.PlaygoApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtilOtp {

    private static final SessionFactory factory = new Configuration().configure("hibernate-sql.cfg.xml").buildSessionFactory();

    public static String getOTP() {
        Session session = factory.openSession();
        String result = "";

        try {
            Query query = session.
                    createSQLQuery("select pesan from dbo.inbox where pesan like '%Kode anda%' order by tgl_entri desc");
            query.setMaxResults(1);

            List results = query.getResultList();
            result = get4DigitsNumericFromMessage(results.get(0).toString());
            PlaygoApplication.logger.info("Generated OTP : "+result);
        } catch (Exception e) {
            e.printStackTrace();
            PlaygoApplication.logger.info(e.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    private static String get4DigitsNumericFromMessage(String input) throws Exception {
        String otp = "";
        String regex = "(?<=\\s)\\d{4}(?=\\s)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            otp = input.substring(matcher.start(), matcher.end());
        }

        return otp;
    }

}
