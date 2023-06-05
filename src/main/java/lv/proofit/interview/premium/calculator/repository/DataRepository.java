package lv.proofit.interview.premium.calculator.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.javaparser.utils.Pair;

import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskType;
import lv.proofit.interview.premium.calculator.utils.GroovyHelper;

@Component
public class DataRepository {
	private static final String BASE_SCRIPT = "BaseScript.groovy";
	private final GroovyHelper groovyHelper;

	@Autowired
	public DataRepository(GroovyHelper groovyHelper) {
		this.groovyHelper = groovyHelper;
	}

	public BigDecimal findPremiumByRiskType(RiskType riskType) {
		ArrayList<LinkedHashMap<String, Serializable>> riskBasePremiumData = (ArrayList<LinkedHashMap<String, Serializable>>) groovyHelper.executeScript(
				BASE_SCRIPT, "getRiskBasePremiumData", null);
		BigDecimal premium = (BigDecimal) riskBasePremiumData.stream()
				.filter(risk -> risk.get("RISK_TYPE").equals(riskType.getValue())).findFirst()
				.map(risk -> risk.get("PREMIUM")).orElse(null);

		return premium;
	}

	public Pair<BigDecimal, BigDecimal> findSumInsuredFactors(BigDecimal sumInsured) {
		ArrayList<LinkedHashMap<String, Serializable>> sumInsuredFactorData = (ArrayList<LinkedHashMap<String, Serializable>>) groovyHelper.executeScript(
				BASE_SCRIPT, "getSumInsuredFactorData", null);

		Pair<BigDecimal, BigDecimal> factor = sumInsuredFactorData.stream()
				.filter(sumInsuredFactor -> sumInsured.intValue() >= (Integer) sumInsuredFactor.get("VALUE_FROM")
						&& sumInsured.intValue() >= (Integer) sumInsuredFactor.get("VALUE_FROM"))
				.findFirst()
				.map(sumInsuredFactor -> new Pair<>((BigDecimal) sumInsuredFactor.get("FACTOR_MIN"),
						(BigDecimal) sumInsuredFactor.get("FACTOR_MAX"))).get();

		return factor;
	}

	public Pair<BigDecimal, BigDecimal> findAgeFactors(String make, String model, int currentYear) {
		ArrayList<LinkedHashMap<String, Serializable>> ageFactors = (ArrayList<LinkedHashMap<String, Serializable>>) groovyHelper.executeScript(
				BASE_SCRIPT, "getAgeFactorData", null);

		Pair<BigDecimal, BigDecimal> factor = ageFactors.stream()
				.filter(ageFactor -> currentYear >= (Integer) ageFactor.get("VALUE_FROM")
						&& currentYear <= (Integer) ageFactor.get("VALUE_FROM") && make.equals(ageFactor.get("MAKE"))
						&& model.equals(ageFactor.get("MODEL")))
				.findFirst().map(ageFactor -> new Pair<>((BigDecimal) ageFactor.get("FACTOR_MIN"),
						(BigDecimal) ageFactor.get("FACTOR_MAX"))).orElse(null);

		return factor;
	}

}
