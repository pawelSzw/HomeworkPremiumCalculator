package lv.proofit.interview.premium.calculator.service.policy.object;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskCalculator;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskCalculatorFactory;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskType;
import lv.proofit.interview.premium.calculator.web.model.Bicycle;
import lv.proofit.interview.premium.calculator.web.model.PremiumObject;
import lv.proofit.interview.premium.calculator.web.model.Risk;

@Service
public class BicycleCalculator implements PremiumObjectCalculator<Bicycle> {

	private final BicycleValidator bicycleValidator;

	private RiskCalculatorFactory riskCalculatorFactory;

	@Autowired
	public BicycleCalculator(BicycleValidator bicycleValidator, RiskCalculatorFactory riskCalculatorFactory) {
		this.bicycleValidator = bicycleValidator;
		this.riskCalculatorFactory = riskCalculatorFactory;
	}

	public PremiumObject calculate(Bicycle bicycle) {
		bicycleValidator.validateBicycle(bicycle);

		Function<Risk, Risk> calculateRisk = risk -> {
			RiskCalculator riskCalculator = riskCalculatorFactory.getRiskCalculator(
					RiskType.valueOf(risk.getRiskType()));
			Risk riskWithSumInsured = Risk.builder().riskType(risk.getRiskType())
					.sumInsured(riskCalculator.calculateSumInsured(bicycle))
					.premium(riskCalculator.calculatePremium(bicycle)).build();
			return riskWithSumInsured;
		};

		List<Risk> risks = bicycle.getRisks().stream().map(
				risk -> calculateRisk.apply(risk)).collect(Collectors.toList());

		BigDecimal premium = risks.stream().map(Risk::getPremium).reduce(BigDecimal.ZERO, BigDecimal::add);
		BigDecimal sumInured = risks.stream().map(Risk::getSumInsured).reduce(BigDecimal.ZERO, BigDecimal::add);
		return PremiumObject.builder().premium(premium).sumInsured(sumInured).risks(risks).build();
	}
}
