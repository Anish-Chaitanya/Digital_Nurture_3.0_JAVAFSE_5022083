import java.util.HashMap;
import java.util.Map;

// Define the Product class
class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product{" +
               "productId='" + productId + '\'' +
               ", productName='" + productName + '\'' +
               ", quantity=" + quantity +
               ", price=" + price +
               '}';
    }
}

// Define the InventoryManager class
class InventoryManager {
    private Map<String, Product> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    // Add a product
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    // Update a product
    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete a product
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    // Retrieve a product
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    // List all products
    public void listProducts() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}

// Main class to demonstrate the inventory management system
public class InventoryManagementSystem {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        // Create products
        Product p1 = new Product("001", "Laptop", 10, 999.99);
        Product p2 = new Product("002", "Smartphone", 25, 499.99);

        // Add products to inventory
        manager.addProduct(p1);
        manager.addProduct(p2);

        // List all products
        System.out.println("Initial inventory:");
        manager.listProducts();

        // Update a product
        p1.setQuantity(8);
        manager.updateProduct(p1);

        // Retrieve and display a product
        System.out.println("\nUpdated product details:");
        System.out.println(manager.getProduct("001"));

        // Delete a product
        manager.deleteProduct("002");

        // List all products after deletion
        System.out.println("\nInventory after deletion:");
        manager.listProducts();
    }
}
