package lv.proofit.interview.premium.calculator.service.policy;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.proofit.interview.premium.calculator.exception.PremiumCalculatorValidationException;
import lv.proofit.interview.premium.calculator.web.model.PremiumRequest;

@Service
public class PremiumRequestValidator {

    private Validator validator;

    @Autowired
	public PremiumRequestValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
    }

    public void validate(final PremiumRequest premiumRequest){
        final Set<ConstraintViolation<PremiumRequest>> constraints = this.validator.validate(premiumRequest);
        if(!constraints.isEmpty()){
            throw new PremiumCalculatorValidationException("Premium request is invalid.", constraints);
        }
    }
}
