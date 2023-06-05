package lv.proofit.interview.premium.calculator;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PremiumCalculatorApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PremiumCalculatorApplication.class);
	}

	public static void main(String[] args) {
		new SpringApplicationBuilder(PremiumCalculatorApplication.class)
				.run(args);
	}
}
