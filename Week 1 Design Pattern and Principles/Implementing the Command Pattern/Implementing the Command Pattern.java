public class CommandPatternExample {

    // Command Interface
    public interface Command {
        void execute();
    }

    // Receiver Class
    public static class Light {
        public void turnOn() {
            System.out.println("The light is ON");
        }

        public void turnOff() {
            System.out.println("The light is OFF");
        }
    }

    // Concrete Command to turn on the light
    public static class LightOnCommand implements Command {
        private Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    // Concrete Command to turn off the light
    public static class LightOffCommand implements Command {
        private Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

    // Invoker Class
    public static class RemoteControl {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            if (command != null) {
                command.execute();
            } else {
                System.out.println("No command set.");
            }
        }
    }

    // Test Class
    public static void main(String[] args) {
        Light light = new Light();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);

        RemoteControl remote = new RemoteControl();

        // Turn on the light
        remote.setCommand(lightOn);
        remote.pressButton();

        // Turn off the light
        remote.setCommand(lightOff);
        remote.pressButton();
    }
}

