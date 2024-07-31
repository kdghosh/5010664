package sai;

public class FinancialForecasting {

    // Recursive method to calculate future value
    public static double calculateFutureValue(double presentValue, double growthRate, int periods) {
        // Base case: If no more periods, return present value
        if (periods == 0) {
            return presentValue;
        }
        // Recursive case: Calculate future value for one period less
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        double presentValue = 1000.0;  // Example present value
        double growthRate = 0.05;      // Example growth rate (5%)
        int periods = 10;              // Example number of periods (years)

        double futureValue = calculateFutureValue(presentValue, growthRate, periods);
        System.out.println("Future Value: " + futureValue);
    }
}
