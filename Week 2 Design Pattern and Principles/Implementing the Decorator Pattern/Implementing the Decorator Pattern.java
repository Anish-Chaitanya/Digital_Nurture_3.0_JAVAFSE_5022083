public class DecoratorPatternExample {

    // Component Interface
    public interface Notifier {
        void send(String message);
    }

    // Concrete Component
    public static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Sending Email with message: " + message);
        }
    }

    // Abstract Decorator
    public static abstract class NotifierDecorator implements Notifier {
        protected Notifier wrappedNotifier;

        public NotifierDecorator(Notifier notifier) {
            this.wrappedNotifier = notifier;
        }

        @Override
        public void send(String message) {
            wrappedNotifier.send(message);
        }
    }

    // Concrete Decorator for SMS
    public static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            sendSMS(message);
        }

        private void sendSMS(String message) {
            System.out.println("Sending SMS with message: " + message);
        }
    }

    // Concrete Decorator for Slack
    public static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            sendSlackMessage(message);
        }

        private void sendSlackMessage(String message) {
            System.out.println("Sending Slack message with message: " + message);
        }
    }

    // Test Class
    public static void main(String[] args) {
        // Basic email notification
        Notifier notifier = new EmailNotifier();
        notifier.send("Hello via Email!");

        // Email + SMS notification
        Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());
        smsNotifier.send("Hello via Email and SMS!");

        // Email + SMS + Slack notification
        Notifier slackNotifier = new SlackNotifierDecorator(new SMSNotifierDecorator(new EmailNotifier()));
        slackNotifier.send("Hello via Email, SMS, and Slack!");
    }
}

