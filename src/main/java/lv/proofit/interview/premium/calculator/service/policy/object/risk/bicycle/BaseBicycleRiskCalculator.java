package lv.proofit.interview.premium.calculator.service.policy.object.risk.bicycle;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.javaparser.utils.Pair;

import lv.proofit.interview.premium.calculator.repository.DataRepository;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskCalculator;
import lv.proofit.interview.premium.calculator.web.model.Bicycle;

public abstract class BaseBicycleRiskCalculator implements RiskCalculator<Bicycle> {
	protected final DataRepository dataRepository;

	@Autowired
	protected BaseBicycleRiskCalculator(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@Override
	public BigDecimal calculateSumInsuredFactor(Bicycle bicycle) {
		int currentYear = Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0, 4));
		int age = currentYear - bicycle.getManufactureYear();
		Pair<BigDecimal, BigDecimal> sumInsuredFactors = dataRepository.findSumInsuredFactors(bicycle.getSumInsured());

		//TO DO calculate sum insured factor
		return BigDecimal.ONE;
	}
}
