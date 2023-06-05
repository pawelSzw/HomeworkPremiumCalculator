package lv.proofit.interview.premium.calculator.service.policy.object.risk.bicycle;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.proofit.interview.premium.calculator.repository.DataRepository;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskType;
import lv.proofit.interview.premium.calculator.web.model.Bicycle;

@Service("DAMAGE")
class DamageCalculatorBicycle extends BaseBicycleRiskCalculator {

	@Autowired
	public DamageCalculatorBicycle(DataRepository dataRepository) {
		super(dataRepository);
	}
	@Override
	public BigDecimal calculatePremium(Bicycle bicycle) {
		return dataRepository.findPremiumByRiskType(getRiskType()).multiply(calculateSumInsuredFactor(bicycle)).multiply(calculateBicycleAgeFactor(bicycle));
	}

	@Override
	public BigDecimal calculateSumInsured(Bicycle bicycle) {
		return bicycle.getSumInsured().divide(BigDecimal.valueOf(2));
	}

	private BigDecimal calculateBicycleAgeFactor(Bicycle bicycle) {
		//TO DO implement
		return BigDecimal.ONE;
	}

	@Override
	public RiskType getRiskType() {
		return  RiskType.DAMAGE;
	}
}

