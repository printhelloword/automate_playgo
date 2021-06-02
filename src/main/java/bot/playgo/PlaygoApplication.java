package bot.playgo;

import bot.playgo.Utility.DBUtilOtp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class PlaygoApplication {
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlaygoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PlaygoApplication.class, args);
//		public static Date formattedRequestDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Timestamp(new Date().getTime()).toString());
	}

}
