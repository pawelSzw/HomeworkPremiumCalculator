package lv.proofit.interview.premium.calculator.web.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class PremiumRequest {
	@Size(min = 1, message = "At least one object should be present")
	@Valid
	List<Bicycle> bicycles;
}
