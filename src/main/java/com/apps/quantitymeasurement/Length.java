package com.apps.quantitymeasurement;

public class Length {

    private final double value;
    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;

    // Enum to represent supported units of length
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }
    
    // Constructor to initialize Length with value and unit
    public Length(double value, LengthUnit unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    private boolean compare(Length that) {
        return Math.abs(this.convertToBaseUnit() - that.convertToBaseUnit()) < EPSILON;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Length)) return false;

        Length that = (Length) o;
        return compare(that);
    }

    @Override
    public int hashCode() {
        long normalized = Math.round(convertToBaseUnit() / EPSILON);
        return Long.hashCode(normalized);
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit);
    }

    // Convert to target unit
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = convertToBaseUnit();
        double converted = baseValue / targetUnit.getConversionFactor();
        converted = round(converted);

        return new Length(converted, targetUnit);
    }

    // Static numeric conversion
    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (source == null || target == null)
            throw new IllegalArgumentException("Units cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        double baseValue = value * source.getConversionFactor();
        double result = baseValue / target.getConversionFactor();
        return Math.round(result * 100.0) / 100.0;
    }

    // UC6: Add and return in first operand unit
    public Length add(Length that) {
        if (that == null)
            throw new IllegalArgumentException("Operand cannot be null");

        double sumInBase = this.convertToBaseUnit() + that.convertToBaseUnit();
        double result = sumInBase / this.unit.getConversionFactor();
        result = round(result);

        return new Length(result, this.unit);
    }

    // UC7: Add and return in specified target unit
    public Length add(Length that, LengthUnit targetUnit) {
        if (that == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double sumInBase = this.convertToBaseUnit() + that.convertToBaseUnit();
        double result = sumInBase / targetUnit.getConversionFactor();
        result = round(result);

        return new Length(result, targetUnit);
    }

    private double round(double value) {
        return Math.round(value * 1000000.0) / 1000000.0;
    }
}