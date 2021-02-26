package util;

// helper methods to substitute the Java.Math stuff I wasn't allowed to use

public class MathHelpers {

    // returns num raised to the specified power
    public static double raiseToPower(double num, int power) {
        double result =  num;

        for (int i = 1; i < power; i++) {
            result *= num;
        }
        return result;
    }

    // returns the absolute difference between two numbers
    private static double absDiff(double num1,double num2) {
        return num1 > num2 ? num1 - num2 : num2 - num1;
    }

    // uses binary search to calculate the Nth root of the number with desired precision
    // doesn't work with negative numbers but I assume that's acceptable in the context of loans
    public static double nthRoot(double num, int root, double precision) {

        double lowerBound = 0;
        double upperBound = num < 1 ? 1 : num;

        while(true) {
            double guess = (upperBound + lowerBound) / 2;
            double test = raiseToPower(guess, root);

            if (absDiff(test, num) < precision) {
                return guess;
            }

            if (test - num > 0) {
                upperBound = guess;
            } else {
                lowerBound = guess;
            }
        }
    }
}
