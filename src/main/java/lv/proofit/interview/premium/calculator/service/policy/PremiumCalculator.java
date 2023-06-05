package lv.proofit.interview.premium.calculator.service.policy;

import lv.proofit.interview.premium.calculator.service.policy.object.BicycleCalculator;
import lv.proofit.interview.premium.calculator.web.model.PremiumObject;
import lv.proofit.interview.premium.calculator.web.model.PremiumRequest;
import lv.proofit.interview.premium.calculator.web.model.PremiumResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PremiumCalculator {

	private PremiumRequestValidator premiumRequestValidator;

	private BicycleCalculator bicycleCalculator;

	@Autowired
	public PremiumCalculator(PremiumRequestValidator premiumRequestValidator, BicycleCalculator bicycleCalculator) {
		this.premiumRequestValidator = premiumRequestValidator;
		this.bicycleCalculator = bicycleCalculator;
	}

	public PremiumResponse calculate(final PremiumRequest premiumRequest) {

		premiumRequestValidator.validate(premiumRequest);

		List<PremiumObject> premiumObjects = premiumRequest.getBicycles().stream()
				.map(bicycle -> bicycleCalculator.calculate(bicycle)).collect(
						Collectors.toList());

		BigDecimal premium = premiumObjects.stream().map(PremiumObject::getPremium)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		return PremiumResponse
				.builder()
				.objects(premiumObjects)
				.premium(premium)
				.build();
	}
}
