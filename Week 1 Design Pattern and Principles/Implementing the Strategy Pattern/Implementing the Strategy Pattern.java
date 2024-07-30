public class StrategyPatternExample {

    // Strategy Interface
    public interface PaymentStrategy {
        void pay(double amount);
    }

    // Concrete Strategy for Credit Card Payment
    public static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;
        private String cardHolderName;

        public CreditCardPayment(String cardNumber, String cardHolderName) {
            this.cardNumber = cardNumber;
            this.cardHolderName = cardHolderName;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paying " + amount + " using Credit Card.");
            System.out.println("Card Number: " + cardNumber);
            System.out.println("Card Holder: " + cardHolderName);
        }
    }

    // Concrete Strategy for PayPal Payment
    public static class PayPalPayment implements PaymentStrategy {
        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paying " + amount + " using PayPal.");
            System.out.println("PayPal Email: " + email);
        }
    }

    // Context Class
    public static class PaymentContext {
        private PaymentStrategy paymentStrategy;

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void executePayment(double amount) {
            if (paymentStrategy == null) {
                throw new IllegalStateException("Payment strategy not set.");
            }
            paymentStrategy.pay(amount);
        }
    }

    // Test Class
    public static void main(String[] args) {
        PaymentContext paymentContext = new PaymentContext();

        // Set strategy to Credit Card Payment
        paymentContext.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432", "John Doe"));
        paymentContext.executePayment(100.0);

        System.out.println(); // for separating outputs

        // Set strategy to PayPal Payment
        paymentContext.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        paymentContext.executePayment(200.0);
    }
}

