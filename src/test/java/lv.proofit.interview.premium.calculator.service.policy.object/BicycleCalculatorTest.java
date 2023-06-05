package lv.proofit.interview.premium.calculator.service.policy.object;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskCalculator;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskCalculatorFactory;
import lv.proofit.interview.premium.calculator.service.policy.object.risk.RiskType;
import lv.proofit.interview.premium.calculator.web.model.Bicycle;
import lv.proofit.interview.premium.calculator.web.model.PremiumObject;
import lv.proofit.interview.premium.calculator.web.model.Risk;

@RunWith(MockitoJUnitRunner.class)
public class BicycleCalculatorTest {

	private BicycleCalculator bicycleCalculator;

	@Mock
	private BicycleValidator bicycleValidator;

	@Mock
	private RiskCalculatorFactory riskCalculatorFactory;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		bicycleCalculator = new BicycleCalculator(bicycleValidator, riskCalculatorFactory);
	}

	@Test
	void testCalculate() {
		// Mocked input data
		Bicycle bicycle = new Bicycle();
		Risk risk1 = Risk.builder().riskType(RiskType.DAMAGE.name()).build();
		Risk risk2 = Risk.builder().riskType(RiskType.THIRD_PARTY_DAMAGE.name()).build();
		bicycle.setRisks(Arrays.asList(risk1, risk2));

		// Mocked dependencies
		RiskCalculator riskCalculator1 = mock(RiskCalculator.class);
		RiskCalculator riskCalculator2 = mock(RiskCalculator.class);
		when(riskCalculatorFactory.getRiskCalculator(RiskType.DAMAGE)).thenReturn(riskCalculator1);
		when(riskCalculatorFactory.getRiskCalculator(RiskType.THIRD_PARTY_DAMAGE)).thenReturn(riskCalculator2);

		// Mocked risk calculations
		when(riskCalculator1.calculateSumInsured(bicycle)).thenReturn(BigDecimal.valueOf(100));
		when(riskCalculator1.calculatePremium(bicycle)).thenReturn(BigDecimal.valueOf(50));
		when(riskCalculator2.calculateSumInsured(bicycle)).thenReturn(BigDecimal.valueOf(200));
		when(riskCalculator2.calculatePremium(bicycle)).thenReturn(BigDecimal.valueOf(100));

		// Perform the calculation
		PremiumObject result = bicycleCalculator.calculate(bicycle);

		// Verify the expected behavior
		verify(bicycleValidator).validateBicycle(bicycle);
		verify(riskCalculatorFactory).getRiskCalculator(RiskType.DAMAGE);
		verify(riskCalculatorFactory).getRiskCalculator(RiskType.THIRD_PARTY_DAMAGE);
		verify(riskCalculator1).calculateSumInsured(bicycle);
		verify(riskCalculator1).calculatePremium(bicycle);
		verify(riskCalculator2).calculateSumInsured(bicycle);
		verify(riskCalculator2).calculatePremium(bicycle);

		// Verify the result
		List<Risk> expectedRisks = Arrays.asList(
				Risk.builder().riskType(RiskType.DAMAGE.name()).sumInsured(BigDecimal.valueOf(100)).premium(BigDecimal.valueOf(50)).build(),
				Risk.builder().riskType(RiskType.THIRD_PARTY_DAMAGE.name()).sumInsured(BigDecimal.valueOf(200)).premium(BigDecimal.valueOf(100)).build()
		);
		BigDecimal expectedPremium = BigDecimal.valueOf(150);
		BigDecimal expectedSumInsured = BigDecimal.valueOf(300);
		assertEquals(expectedRisks.size(), result.getRisks().size());
		assertEquals(expectedPremium, result.getPremium());
		assertEquals(expectedSumInsured, result.getSumInsured());
	}
}
