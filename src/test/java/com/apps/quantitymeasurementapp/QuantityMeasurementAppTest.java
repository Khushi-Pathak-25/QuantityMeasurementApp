package com.apps.quantitymeasurementapp;

import org.junit.jupiter.api.Test;
import com.apps.quantitymeasurement.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // Equality Tests

    @Test
    public void testEquality_YardToFeet() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        Length feet = new Length(3.0, Length.LengthUnit.FEET);
        assertEquals(yard, feet);
    }

    @Test
    public void testEquality_CentimeterToInch() {
        Length cm = new Length(2.54, Length.LengthUnit.CENTIMETERS);
        Length inch = new Length(1.0, Length.LengthUnit.INCHES);
        assertEquals(cm, inch);
    }

    @Test
    public void testEquality_DifferentValues() {
        Length feet1 = new Length(1.0, Length.LengthUnit.FEET);
        Length feet2 = new Length(2.0, Length.LengthUnit.FEET);
        assertNotEquals(feet1, feet2);
    }

    @Test
    public void testEquality_NullComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertNotEquals(feet, null);
    }

    // Conversion Tests

    @Test
    public void testConversion_FeetToInches() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(12.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testConversion_InchesToFeet() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(24.0,
                        Length.LengthUnit.INCHES,
                        Length.LengthUnit.FEET);

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testConversion_SameUnit() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(5.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.FEET);

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testConversion_NegativeValue() {
        Length result = QuantityMeasurementApp
                .demonstrateLengthConversion(-1.0,
                        Length.LengthUnit.FEET,
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(-12.0, Length.LengthUnit.INCHES), result);
    }

    // UC6 Addition Tests

    @Test
    public void testAddition_SameUnit() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(2.0, Length.LengthUnit.FEET),
                new Length(3.0, Length.LengthUnit.FEET));

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_CrossUnit() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES));

        assertEquals(new Length(2.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_WithZero() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(0.0, Length.LengthUnit.INCHES));

        assertEquals(new Length(5.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NegativeValues() {
        Length result = QuantityMeasurementApp.demonstrateLengthAddition(
                new Length(5.0, Length.LengthUnit.FEET),
                new Length(-2.0, Length.LengthUnit.FEET));

        assertEquals(new Length(3.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_NullOperand() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.demonstrateLengthAddition(
                        new Length(1.0, Length.LengthUnit.FEET),
                        null));
    }

    // UC7 Addition With Target Unit

    @Test
    public void testAddition_WithTargetUnit_ToInches() {
        Length result = new Length(1.0, Length.LengthUnit.FEET)
                .add(new Length(12.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.INCHES);

        assertEquals(new Length(24.0, Length.LengthUnit.INCHES), result);
    }

    @Test
    public void testAddition_WithTargetUnit_ToFeet() {
        Length result = new Length(1.0, Length.LengthUnit.YARDS)
                .add(new Length(3.0, Length.LengthUnit.FEET),
                        Length.LengthUnit.FEET);

        assertEquals(new Length(6.0, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testAddition_WithTargetUnit_ToCentimeters() {
        Length result = new Length(2.54, Length.LengthUnit.CENTIMETERS)
                .add(new Length(1.0, Length.LengthUnit.INCHES),
                        Length.LengthUnit.CENTIMETERS);

        assertEquals(new Length(5.08, Length.LengthUnit.CENTIMETERS), result);
    }

    @Test
    public void testAddition_WithTargetUnit_NullTarget() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, Length.LengthUnit.FEET)
                        .add(new Length(1.0, Length.LengthUnit.FEET), null));
    }

    // Edge Cases

    @Test
    public void testLargeValuesAddition() {
        Length result = new Length(1e6, Length.LengthUnit.FEET)
                .add(new Length(1e6, Length.LengthUnit.FEET));

        assertEquals(new Length(2e6, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testSmallValuesAddition() {
        Length result = new Length(0.001, Length.LengthUnit.FEET)
                .add(new Length(0.002, Length.LengthUnit.FEET));

        assertEquals(new Length(0.003, Length.LengthUnit.FEET), result);
    }

    @Test
    public void testInvalidConstructor_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    @Test
    public void testInvalidConstructor_NaN() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, Length.LengthUnit.FEET));
    }

    @Test
    public void testInvalidConstructor_Infinity() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.POSITIVE_INFINITY, Length.LengthUnit.FEET));
    }
}