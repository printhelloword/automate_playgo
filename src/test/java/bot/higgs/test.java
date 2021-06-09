package bot.higgs;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {
        String stringDate="2021-06-28 09:33:53.337";
        Date formattedEntryDate=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(stringDate);

        Date formattedRequestDateTimestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Timestamp(new Date().getTime()).toString());

        System.out.println("Request Timestamp : " +formattedRequestDateTimestamp);
        System.out.println("Latest SMS Entry Date : " +formattedEntryDate);
        System.out.println("Is OTP Newer than Request Date : " +formattedEntryDate.after(formattedRequestDateTimestamp));
    }
}
