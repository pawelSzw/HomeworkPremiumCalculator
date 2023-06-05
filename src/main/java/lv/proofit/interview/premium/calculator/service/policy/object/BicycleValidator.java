package lv.proofit.interview.premium.calculator.service.policy.object;

import static lv.proofit.interview.premium.calculator.config.PremiumCalculatorConfig.BICYCLE_MAX_SUM_INSURED;

import org.springframework.stereotype.Service;

import lv.proofit.interview.premium.calculator.exception.PremiumCalculatorValidationException;
import lv.proofit.interview.premium.calculator.web.model.Bicycle;

@Service
public class BicycleValidator {

	public void validateManufactureYear(int manufactureYear) {
		int currentYear = Integer.parseInt(String.valueOf(System.currentTimeMillis()).substring(0, 4));
		if (manufactureYear < currentYear - 1) {
			throw new PremiumCalculatorValidationException("Bicycle manufacture year is invalid");
		}
	}

	public void validateSumInsured(Bicycle bicycle) {
		if (bicycle.getSumInsured().compareTo(BICYCLE_MAX_SUM_INSURED) > 0) {
			throw new PremiumCalculatorValidationException("Bicycle sum insured is invalid");
		}
	}

	public void validateBicycle(Bicycle bicycle) {
		validateManufactureYear(bicycle.getManufactureYear());
		validateSumInsured(bicycle);
	}
}
