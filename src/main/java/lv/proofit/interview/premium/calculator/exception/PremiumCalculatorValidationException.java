package lv.proofit.interview.premium.calculator.exception;

import lv.proofit.interview.premium.calculator.web.model.PremiumRequest;

import lombok.Getter;

import javax.validation.ConstraintViolation;

import java.util.Set;

@Getter
public class PremiumCalculatorValidationException extends RuntimeException {

	private Set<ConstraintViolation<PremiumRequest>> policyValidationErrors;

	public PremiumCalculatorValidationException(final String message,
			final Set<ConstraintViolation<PremiumRequest>> policyValidationErrors) {
		super(message);
		this.policyValidationErrors = policyValidationErrors;
	}

	public PremiumCalculatorValidationException(final String message) {
		super(message);
	}
}