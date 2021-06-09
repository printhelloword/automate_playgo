package bot.higgs.Utility;

import bot.higgs.AovApplication;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBUtilOtp {

    private static final SessionFactory factory = new Configuration().configure("hibernate-sql.cfg.xml").buildSessionFactory();

    public static String getOTP(String kode_terminal) {
        Session session = factory.openSession();
        String result = "";

        String otp = Components.stringFalse;
        String entryDate = Components.stringFalse;

        try {
            Query query = session.
                    createSQLQuery("select pesan, tgl_entri from dbo.inbox where pesan like '%Kode anda%' and kode_terminal=:kode_terminal order by tgl_entri desc");
            query.setParameter("kode_terminal", kode_terminal);
            query.setMaxResults(1);

//            List results = query.getResultList();
            List<Object[]> results = query.getResultList();

            for (Object[] row : results) {
                otp = row[0].toString();
                entryDate = row[1].toString();
                AovApplication.logger.info("Generated OTP : " + otp);
            }

            if (isCurrentOtpEntryDateNewer(entryDate))
                result = get4DigitsNumericFromMessage(otp);
            else
                result = Components.stringFalse;

            /*result = get4DigitsNumericFromMessage(results.get(0).toString());
            PlaygoApplication.logger.info("Generated OTP : " + result);
            PlaygoApplication.logger.info("Entry Date : " + results.get(0).toString());*/

        } catch (Exception e) {
            e.printStackTrace();
            AovApplication.logger.info(e.getMessage());
        } finally {
            session.close();
        }
        return result;
    }

    private static boolean isCurrentOtpEntryDateNewer(String entryDate) throws ParseException {
        Date formattedEntryDate = new SimpleDateFormat(Components.dateFormatPattern).parse(entryDate);

        AovApplication.logger.info("<< Comparing Formatted OTP Entry Date - Request Timestamp >>");
        AovApplication.logger.info("Request Timestamp  = " + Components.formattedRequestTimestamp);
        AovApplication.logger.info("OTP Entry Date = " + formattedEntryDate);

        return formattedEntryDate.after(Components.formattedRequestTimestamp);
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
