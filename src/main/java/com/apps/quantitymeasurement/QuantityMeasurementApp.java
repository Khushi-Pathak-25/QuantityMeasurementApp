package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

	   // Static method to demonstrate Length equality
		public static boolean demonstrateLengthEquality(Length l1, Length l2) {
			return l1.equals(l2);
		}

		// Static method to demonstrate extended unit comparisons
		public static boolean demonstrateLengthComparison(double value1, Length.LengthUnit unit1, 
														  double value2, Length.LengthUnit unit2) {
			Length l1 = new Length(value1, unit1);
			Length l2 = new Length(value2, unit2);
			boolean result = l1.equals(l2);
			System.out.println(l1 + " == " + l2 + " ? " + result);
			return result;
		}

		// Static method to demonstrate conversion using raw values
		public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, 
																	   Length.LengthUnit toUnit) { 
			Length source = new Length(value, fromUnit); 
			Length converted = source.convertTo(toUnit); 
			System.out.println(source + " -> " + converted); 
			return converted; 
		}
		
		// Static method to demonstrate conversion using an existing Length object
		public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) { 
			Length converted = length.convertTo(toUnit); 
			System.out.println(length + " -> " + converted); 
			return converted; 
		}
		
		// Static method to demonstrate addition of two Length objects
		public static Length demonstrateLengthAddition(Length length1, Length length2) {
		    Length sum = length1.add(length2); // use Length.add() from UC6
		    System.out.println(length1 + " + " + length2 + " = " + sum);
		    return sum;
		}
		
       // UC7 demonstration
       public static Length demonstrateLengthAddition(
            Length length1,
            Length length2,
            Length.LengthUnit targetUnit) {

        Length result = length1.add(length2, targetUnit);
        System.out.println(length1 + " + " + length2 +
                " = " + result + " (in " + targetUnit + ")");
        return result;
    }

    public static void main(String[] args) {

        // Equality
        demonstrateLengthComparison(1.0, Length.LengthUnit.FEET,
                                    12.0, Length.LengthUnit.INCHES);

        // Conversion
        demonstrateLengthConversion(1.0,
                Length.LengthUnit.YARDS,
                Length.LengthUnit.FEET);

        // UC6 Addition
        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES)
        );

        // UC7 Addition with Target Unit
        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.FEET),
                new Length(12.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.INCHES
        );

        demonstrateLengthAddition(
                new Length(1.0, Length.LengthUnit.YARDS),
                new Length(3.0, Length.LengthUnit.FEET),
                Length.LengthUnit.FEET
        );

        demonstrateLengthAddition(
                new Length(2.54, Length.LengthUnit.CENTIMETERS),
                new Length(1.0, Length.LengthUnit.INCHES),
                Length.LengthUnit.CENTIMETERS
        );
    }
}