package lv.proofit.interview.premium.calculator.service.policy.object.risk;

public interface RiskCalculatorFactory {
	RiskCalculator getRiskCalculator(final RiskType riskType);
}
