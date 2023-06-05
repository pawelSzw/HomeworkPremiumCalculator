package lv.proofit.interview.premium.calculator.utils;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import lv.proofit.interview.premium.calculator.config.PremiumCalculatorConfig;

@Component
public class GroovyHelper {

	private final GroovyShell groovyShell;

	@Autowired
	public GroovyHelper(GroovyShell groovyShell) {
		this.groovyShell = groovyShell;
	}

	public Object executeScript(String scriptName, String methodName, Object arg) {
		try {
			Script script = groovyShell.parse(new File(PremiumCalculatorConfig.GROOVY_SCRIPT_PATH, scriptName));
			Binding binding = new Binding();
			binding.setVariable("arg", arg);
			script.setBinding(binding);
			return script.invokeMethod(methodName, arg);
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}