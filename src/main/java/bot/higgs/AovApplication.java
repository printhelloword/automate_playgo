package bot.higgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AovApplication {
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AovApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AovApplication.class, args);
//		public static Date formattedRequestDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Timestamp(new Date().getTime()).toString());
	}

}
