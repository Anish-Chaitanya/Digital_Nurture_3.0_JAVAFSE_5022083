public class AdapterPatternExample {

    // Target Interface
    public interface PaymentProcessor {
        void processPayment(double amount);
    }

    // Adaptee Class for PayPal
    public static class PayPal {
        public void sendPayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through PayPal.");
        }
    }

    // Adaptee Class for Stripe
    public static class Stripe {
        public void makePayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through Stripe.");
        }
    }

    // Adaptee Class for Square
    public static class Square {
        public void executePayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through Square.");
        }
    }

    // Adapter Class for PayPal
    public static class PayPalAdapter implements PaymentProcessor {
        private PayPal payPal;

        public PayPalAdapter(PayPal payPal) {
            this.payPal = payPal;
        }

        @Override
        public void processPayment(double amount) {
            payPal.sendPayment(amount);
        }
    }

    // Adapter Class for Stripe
    public static class StripeAdapter implements PaymentProcessor {
        private Stripe stripe;

        public StripeAdapter(Stripe stripe) {
            this.stripe = stripe;
        }

        @Override
        public void processPayment(double amount) {
            stripe.makePayment(amount);
        }
    }

    // Adapter Class for Square
    public static class SquareAdapter implements PaymentProcessor {
        private Square square;

        public SquareAdapter(Square square) {
            this.square = square;
        }

        @Override
        public void processPayment(double amount) {
            square.executePayment(amount);
        }
    }

    // Test Class
    public static void main(String[] args) {
        // Using PayPal through its adapter
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPal());
        paypalProcessor.processPayment(100.0);

        // Using Stripe through its adapter
        PaymentProcessor stripeProcessor = new StripeAdapter(new Stripe());
        stripeProcessor.processPayment(200.0);

        // Using Square through its adapter
        PaymentProcessor squareProcessor = new SquareAdapter(new Square());
        squareProcessor.processPayment(300.0);
    }
}
