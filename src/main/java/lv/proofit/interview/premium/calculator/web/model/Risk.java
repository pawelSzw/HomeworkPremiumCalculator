package lv.proofit.interview.premium.calculator.web.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Valid
public class Risk {
	@Pattern(regexp = "THEFT|DAMAGE", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Risk type should be THEFT or DAMAGE")
	private String riskType;

	@Min(value = 0,message = "Sum Insured should be greater than 0")
	private BigDecimal sumInsured;

	@Min(value = 0,message = "Premium should be greater than 0")
	private BigDecimal premium;
}
