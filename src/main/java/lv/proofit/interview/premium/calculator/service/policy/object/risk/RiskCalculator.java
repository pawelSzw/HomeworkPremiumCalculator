package lv.proofit.interview.premium.calculator.service.policy.object.risk;

import java.math.BigDecimal;

import lv.proofit.interview.premium.calculator.web.model.BaseObject;


public interface  RiskCalculator<T extends BaseObject> {
	BigDecimal calculatePremium(T object);

	BigDecimal calculateSumInsured(T object);

	BigDecimal calculateSumInsuredFactor(T object);

	RiskType getRiskType();
}
