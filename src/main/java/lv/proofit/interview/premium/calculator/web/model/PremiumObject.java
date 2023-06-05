package lv.proofit.interview.premium.calculator.web.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Valid
@Builder
public class PremiumObject implements BaseObject {
	private Map<String, String> attributes;
	@NotNull(message = "Premium should not be null")
	private BigDecimal premium;
	@Size(min = 1, message = "At least one risk should be present")
	private List<Risk> risks;
	@NotNull(message = "Sum insured should not be null")
	private BigDecimal sumInsured;

}
