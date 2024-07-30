public class BuilderPatternExample {
    
    // Product class
    public static class Computer {
        // Required parameters
        private String CPU;
        private String RAM;

        // Optional parameters
        private String storage;
        private String graphicsCard;

        // Private constructor
        private Computer(Builder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.graphicsCard = builder.graphicsCard;
        }

        // Getters
        public String getCPU() {
            return CPU;
        }

        public String getRAM() {
            return RAM;
        }

        public String getStorage() {
            return storage;
        }

        public String getGraphicsCard() {
            return graphicsCard;
        }

        @Override
        public String toString() {
            return "Computer [CPU=" + CPU + ", RAM=" + RAM + ", storage=" + storage + ", graphicsCard=" + graphicsCard + "]";
        }

        // Static nested Builder class
        public static class Builder {
            // Required parameters
            private String CPU;
            private String RAM;

            // Optional parameters
            private String storage;
            private String graphicsCard;

            // Constructor for required parameters
            public Builder(String CPU, String RAM) {
                this.CPU = CPU;
                this.RAM = RAM;
            }

            // Methods to set optional parameters
            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGraphicsCard(String graphicsCard) {
                this.graphicsCard = graphicsCard;
                return this;
            }

            // Build method to create Computer instance
            public Computer build() {
                return new Computer(this);
            }
        }
    }

    // Test class
    public static void main(String[] args) {
        // Creating a Computer with only required parameters
        Computer basicComputer = new Computer.Builder("Intel i5", "8GB")
            .build();
        
        // Creating a Computer with optional parameters
        Computer gamingComputer = new Computer.Builder("Intel i7", "16GB")
            .setStorage("1TB SSD")
            .setGraphicsCard("NVIDIA RTX 3080")
            .build();

        // Output the configurations
        System.out.println("Basic Computer: " + basicComputer);
        System.out.println("Gaming Computer: " + gamingComputer);
    }
}

