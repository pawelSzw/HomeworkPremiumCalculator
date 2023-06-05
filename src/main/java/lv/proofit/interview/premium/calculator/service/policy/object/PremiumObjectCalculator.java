package lv.proofit.interview.premium.calculator.service.policy.object;

import lv.proofit.interview.premium.calculator.web.model.BaseObject;
import lv.proofit.interview.premium.calculator.web.model.PremiumObject;

public interface PremiumObjectCalculator<T extends BaseObject> {
	PremiumObject calculate(T object);
}
