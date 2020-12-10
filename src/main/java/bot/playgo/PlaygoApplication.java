package bot.playgo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlaygoApplication {
	public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PlaygoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PlaygoApplication.class, args);
	}

}
