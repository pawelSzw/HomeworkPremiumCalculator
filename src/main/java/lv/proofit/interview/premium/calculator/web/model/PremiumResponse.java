package lv.proofit.interview.premium.calculator.web.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PremiumResponse {
	private List<PremiumObject> objects;
	private BigDecimal premium;
}
