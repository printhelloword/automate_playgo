package bot.higgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HiggsDominoApplication {
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HiggsDominoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HiggsDominoApplication.class, args);
//		public static Date formattedRequestDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Timestamp(new Date().getTime()).toString());
	}

}
