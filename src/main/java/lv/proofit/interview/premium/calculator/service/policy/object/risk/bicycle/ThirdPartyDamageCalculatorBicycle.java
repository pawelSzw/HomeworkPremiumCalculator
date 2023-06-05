package lv.proofit.interview.premium.calculator.service.policy.object.risk.bicycle;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.proofit.interview.premium.calculator.repository.DataRepository;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskType;
import lv.proofit.interview.premium.calculator.web.model.Bicycle;

@Service("THIRD_PARTY_DAMAGE")
class ThirdPartyDamageCalculatorBicycle extends BaseBicycleRiskCalculator {

	@Autowired
	ThirdPartyDamageCalculatorBicycle(DataRepository dataRepository) {
		super(dataRepository);
	}

	@Override
	public BigDecimal calculatePremium(Bicycle bicycle) {
		return dataRepository.findPremiumByRiskType(getRiskType()).multiply(calculateSumInsuredFactor(bicycle))
				.multiply(calculateRiskCountFactor(bicycle));
	}

	@Override
	public BigDecimal calculateSumInsured(Bicycle bicycle) {
		return BigDecimal.valueOf(100);
	}

	private BigDecimal calculateRiskCountFactor(Bicycle bicycle) {
		//TO DO implement
		return BigDecimal.ONE;
	}

	@Override
	public RiskType getRiskType() {
		return RiskType.THIRD_PARTY_DAMAGE;
	}

	;

}

