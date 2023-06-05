package lv.proofit.interview.premium.calculator.config;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskCalculatorFactory;

@Configuration
public class RiskCalculatorConfig {

	@Bean("riskCalculatorFactory")
	public FactoryBean serviceLocatorFactoryBean() {
		ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
		factoryBean.setServiceLocatorInterface(RiskCalculatorFactory.class);
		return factoryBean;
	}

}
