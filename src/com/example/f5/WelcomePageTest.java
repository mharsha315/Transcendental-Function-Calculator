package com.example.f5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for WelcomePage that ensures the calculateF5 method works as expected.
 */
public class WelcomePageTest {

    /** Allowed delta for floating point comparisons. */
    private static final double DELTA = 0.001;
    /** Expected result for positive test case. */
    private static final double EXPECTED_RESULT_FOR_POSITIVE = 18.0;
    /** Expected result for negative exponent test case. */
    private static final double EXPECTED_RESULT_FOR_NEGATIVE_EXPONENT = 1.0 / 3.0;
    /** Expected result for zero base test case. */
    private static final double EXPECTED_RESULT_FOR_ZERO_BASE = 0.0;
    /** Expected result for zero exponent test case. */
    private static final double EXPECTED_RESULT_FOR_ZERO_EXPONENT = 1.0;
    /** Expected result for zero multiplier test case. */
    private static final double EXPECTED_RESULT_FOR_ZERO_MULTIPLIER = 0.0;
    /** Multiplier value for base test case. */
    private static final double BASE_CASE_MULTIPLIER = 2.0;
    /** Base value for base test case. */
    private static final double BASE_CASE_BASE = 3.0;
    /** Exponent value for base test case. */
    private static final double BASE_CASE_EXPONENT = 2.0;
    /** Negative exponent value for test case. */
    private static final double NEGATIVE_EXPONENT = -1.0;
    /** Zero value for test cases. */
    private static final double ZERO = 0.0;
    /** One value for test cases. */
    private static final double ONE = 1.0;
    /** Ten value for test cases. */
    private static final double TEN = 10.0;

    /**
     * Tests the calculateF5 method with positive numbers.
     */
    @Test
    public void testCalculateF5PositiveNumbers() {
        WelcomePage page = new WelcomePage();
        double result = page.calculateF5(BASE_CASE_MULTIPLIER, BASE_CASE_BASE, BASE_CASE_EXPONENT);
        assertEquals(
                EXPECTED_RESULT_FOR_POSITIVE, result, DELTA,
                "Expected 2 times 3 squared to equal 18"
        );
    }

    /**
     * Tests the calculateF5 method with a negative exponent.
     */
    @Test
    public void testCalculateF5NegativeExponent() {
        WelcomePage page = new WelcomePage();
        double result = page.calculateF5(ONE, BASE_CASE_BASE, NEGATIVE_EXPONENT);
        assertEquals(
                EXPECTED_RESULT_FOR_NEGATIVE_EXPONENT, result, DELTA,
                "Expected 3 raised to the power of -1 to equal 1/3"
        );
    }

    /**
     * Tests the calculateF5 method with zero as the base.
     */
    @Test
    public void testCalculateF5ZeroBase() {
        WelcomePage page = new WelcomePage();
        double result = page.calculateF5(ZERO, ZERO, ONE);
        assertEquals(
                EXPECTED_RESULT_FOR_ZERO_BASE, result, DELTA,
                "Expected 0 raised to any power to be 0"
        );
    }

    /**
     * Tests the calculateF5 method with zero as the exponent.
     */
    @Test
    public void testCalculateF5ZeroExponent() {
        WelcomePage page = new WelcomePage();
        double result = page.calculateF5(ONE, TEN, ZERO);
        assertEquals(
                EXPECTED_RESULT_FOR_ZERO_EXPONENT, result, DELTA,
                "Expected any number raised to the power of 0 to be 1"
        );
    }

    /**
     * Tests the calculateF5 method with zero as a multiplier.
     */
    @Test
    public void testCalculateF5ZeroAndNegativeMultiplier() {
        WelcomePage page = new WelcomePage();
        double result = page.calculateF5(ZERO, TEN, NEGATIVE_EXPONENT);
        assertEquals(
                EXPECTED_RESULT_FOR_ZERO_MULTIPLIER, result, DELTA,
                "Expected zero multiplier to result in zero regardless of b or x"
        );
    }
}
