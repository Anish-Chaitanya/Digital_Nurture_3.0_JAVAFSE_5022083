public class ProxyPatternExample {

    // Subject Interface
    public interface Image {
        void display();
    }

    // Real Subject Class
    public static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadImageFromDisk();
        }

        private void loadImageFromDisk() {
            System.out.println("Loading image from disk: " + filename);
            // Simulate loading time
            try {
                Thread.sleep(2000); // 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    // Proxy Class
    public static class ProxyImage implements Image {
        private String filename;
        private RealImage realImage;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

    // Test Class
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // Display image1 for the first time
        System.out.println("Displaying image1 for the first time:");
        image1.display();

        // Display image1 again (should use cached image)
        System.out.println("Displaying image1 again:");
        image1.display();

        // Display image2 for the first time
        System.out.println("Displaying image2 for the first time:");
        image2.display();

        // Display image2 again (should use cached image)
        System.out.println("Displaying image2 again:");
        image2.display();
    }
}

