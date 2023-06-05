package lv.proofit.interview.premium.calculator.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lv.proofit.interview.premium.calculator.exception.PremiumCalculatorValidationException;
import lv.proofit.interview.premium.calculator.service.policy.PremiumCalculator;
import lv.proofit.interview.premium.calculator.web.model.PremiumRequest;
import lv.proofit.interview.premium.calculator.web.model.PremiumResponse;

@RestController
@RequestMapping("/api/vi")
@ControllerAdvice
public class PremiumCalculatorController {

	private PremiumCalculator premiumCalculator;

	private static final Logger logger = LoggerFactory.getLogger(PremiumCalculatorController.class);

	@Autowired
	public PremiumCalculatorController(PremiumCalculator premiumCalculator) {
		this.premiumCalculator = premiumCalculator;
	}

	@PostMapping(path = "/calculate", name = "Calculate premiums for bicycle insurance policies",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PremiumResponse> calculate(@RequestBody @Valid PremiumRequest premiumRequest) {
		PremiumResponse premiumResponse = premiumCalculator.calculate(premiumRequest);
		return new ResponseEntity(premiumResponse, HttpStatus.OK);
	}

	@ExceptionHandler(PremiumCalculatorValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse handleException(
			final PremiumCalculatorValidationException exception) {
		logger.error("Handling  Exception", exception);
		return new ErrorResponse(400, "Bad Request", exception.getMessage());
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse handleException(
			final Exception exception) {
		logger.error("Handling  Exception", exception);
		return new ErrorResponse(500, "Internal Error", exception.getMessage());
	}

}
