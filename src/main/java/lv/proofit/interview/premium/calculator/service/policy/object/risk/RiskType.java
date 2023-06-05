package lv.proofit.interview.premium.calculator.service.policy.object.risk;

public enum RiskType {
	THEFT("THEFT"),
	DAMAGE("DAMAGE"),
	THIRD_PARTY_DAMAGE("THIRD_PARTY_DAMAGE");

	private String value;

	RiskType(String value) {
		this.value = this.name().toUpperCase();
	}

	RiskType from(String riskType) {
		return RiskType.valueOf(riskType.toUpperCase());
	}

	public String getValue() {
		return value.toUpperCase();
	}

}
