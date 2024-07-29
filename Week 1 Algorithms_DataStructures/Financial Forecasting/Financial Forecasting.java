public class FinancialForecasting {

    public static double calculateFutureValueRecursive(double initialValue, double growthRate, int periods) {
        if (periods == 0) {
            return initialValue;
        }
        return calculateFutureValueRecursive(initialValue, growthRate, periods - 1) * (1 + growthRate);
    }

    public static double calculateFutureValueIterative(double initialValue, double growthRate, int periods) {
        double futureValue = initialValue;
        for (int i = 0; i < periods; i++) {
            futureValue *= (1 + growthRate);
        }
        return futureValue;
    }

    public static void main(String[] args) {
        double initialValue = 1000.00;
        double growthRate = 0.05; // 5% growth rate
        int periods = 10;

        double futureValueRecursive = calculateFutureValueRecursive(initialValue, growthRate, periods);
        double futureValueIterative = calculateFutureValueIterative(initialValue, growthRate, periods);

        System.out.printf("Future Value after %d periods (Recursive): %.2f%n", periods, futureValueRecursive);
        System.out.printf("Future Value after %d periods (Iterative): %.2f%n", periods, futureValueIterative);
    }
}
