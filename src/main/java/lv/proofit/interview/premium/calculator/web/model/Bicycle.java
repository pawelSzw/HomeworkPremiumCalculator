package lv.proofit.interview.premium.calculator.web.model;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Valid
public class Bicycle implements BaseObject {
	@NotNull(message = "Make should not be null")
	private String make;
	@NotNull(message = "Model should not be null")
	private String model;
	@Pattern(regexp = "STANDARD|EXTRA", flags = Pattern.Flag.CASE_INSENSITIVE,
			message = "Coverage should be STANDARD or EXTRA")
	private String coverage;
	@NotNull(message = "Manufacture year should not be null")
	private int manufactureYear;
	@Size(min = 1, message = "At least one risk should be present")
	List<Risk> risks;
	@NotNull(message = "Sum insured should not be null")
	private BigDecimal sumInsured;
}