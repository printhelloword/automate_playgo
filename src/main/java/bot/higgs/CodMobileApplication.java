package bot.higgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodMobileApplication {
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CodMobileApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CodMobileApplication.class, args);
//		public static Date formattedRequestDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(new Timestamp(new Date().getTime()).toString());
	}

}
