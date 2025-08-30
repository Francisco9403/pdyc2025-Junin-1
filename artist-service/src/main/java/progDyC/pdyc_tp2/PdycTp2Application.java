package progDyC.pdyc_tp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import progDyC.pdyc_tp2.events.util.PasswordEncoderUtil;

@EnableDiscoveryClient
@SpringBootApplication
public class PdycTp2Application {

	public static void main(String[] args) {
		SpringApplication.run(PdycTp2Application.class, args);
	}
	@Bean
	public PasswordEncoderUtil passwordEncoderUtil() {
		return new PasswordEncoderUtil();
	}
}
