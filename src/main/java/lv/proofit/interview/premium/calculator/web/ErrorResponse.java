package lv.proofit.interview.premium.calculator.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

	private int httpStatus;
	private String title;
	private String description;
}