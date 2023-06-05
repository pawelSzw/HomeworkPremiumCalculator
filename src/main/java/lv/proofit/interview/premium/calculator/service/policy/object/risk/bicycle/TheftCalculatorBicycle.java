package lv.proofit.interview.premium.calculator.service.policy.object.risk.bicycle;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.proofit.interview.premium.calculator.repository.DataRepository;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskType;
import lv.proofit.interview.premium.calculator.web.model.Bicycle;

@Service("THEFT")
class TheftCalculatorBicycle extends BaseBicycleRiskCalculator {

	@Autowired
	public TheftCalculatorBicycle(DataRepository dataRepository) {
		super(dataRepository);
	}
	@Override
	public BigDecimal calculatePremium(Bicycle bicycle) {
		return dataRepository.findPremiumByRiskType(getRiskType()).multiply(calculateSumInsuredFactor(bicycle));
	}

	@Override
	public BigDecimal calculateSumInsured(Bicycle bicycle) {
		return bicycle.getSumInsured();
	}

	@Override
	public RiskType getRiskType() {
		return  RiskType.THEFT;
	}
}

