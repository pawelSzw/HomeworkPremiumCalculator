package lv.proofit.interview.premium.calculator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scripting.groovy.GroovyScriptFactory;

import groovy.lang.GroovyShell;

@Configuration
public class GroovyConfig {

	@Bean
	public GroovyScriptFactory groovyScriptFactory() {
		GroovyScriptFactory scriptFactory = new GroovyScriptFactory(PremiumCalculatorConfig.GROOVY_SCRIPT_PATH);
		scriptFactory.setBeanClassLoader(getClass().getClassLoader());
		return scriptFactory;
	}

	@Bean
	public GroovyShell getGroovyShell() {
		return new GroovyShell();
	}
}
