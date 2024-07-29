import java.util.Arrays;

class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return "Product{" +
               "productId='" + productId + '\'' +
               ", productName='" + productName + '\'' +
               ", category='" + category + '\'' +
               '}';
    }
}

class SearchAlgorithms {
    public static Product linearSearch(Product[] products, String searchTerm) {
        for (Product product : products) {
            if (product.getProductId().equals(searchTerm) || 
                product.getProductName().equals(searchTerm) || 
                product.getCategory().equals(searchTerm)) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, String searchTerm) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = products[mid];

            if (midProduct.getProductId().equals(searchTerm) || 
                midProduct.getProductName().equals(searchTerm) || 
                midProduct.getCategory().equals(searchTerm)) {
                return midProduct;
            }

            if (searchTerm.compareTo(midProduct.getProductId()) < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return null;
    }

    public static void sortProducts(Product[] products) {
        Arrays.sort(products, (p1, p2) -> p1.getProductId().compareTo(p2.getProductId()));
    }
}
//ECommerceSearchDemo.java
public class ECommerceSearchDemo {
    public static void main(String[] args) {
        Product[] products = {
            new Product("001", "Laptop", "Electronics"),
            new Product("002", "Smartphone", "Electronics"),
            new Product("003", "Table", "Furniture"),
            new Product("004", "Chair", "Furniture")
        };

        System.out.println("Linear Search Results:");
        Product linearResult = SearchAlgorithms.linearSearch(products, "Smartphone");
        System.out.println(linearResult);

        System.out.println("\nBinary Search Results:");

        SearchAlgorithms.sortProducts(products);

        Product binaryResult = SearchAlgorithms.binarySearch(products, "003");
        System.out.println(binaryResult);
    }
}

