public class DependencyInjectionExample {

    // Repository Interface
    public interface CustomerRepository {
        Customer findCustomerById(int id);
    }

    // Concrete Repository Implementation
    public static class CustomerRepositoryImpl implements CustomerRepository {
        @Override
        public Customer findCustomerById(int id) {
            // In a real application, this would fetch data from a database
            // For this example, we'll return a dummy customer
            return new Customer(id, "John Doe", "john.doe@example.com");
        }
    }

    // Model Class
    public static class Customer {
        private int id;
        private String name;
        private String email;

        public Customer(int id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        @Override
        public String toString() {
            return "Customer{id=" + id + ", name='" + name + "', email='" + email + "'}";
        }
    }

    // Service Class
    public static class CustomerService {
        private CustomerRepository customerRepository;

        // Constructor injection
        public CustomerService(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        public Customer getCustomerById(int id) {
            return customerRepository.findCustomerById(id);
        }
    }

    // Test Class
    public static void main(String[] args) {
        // Create a concrete repository implementation
        CustomerRepository repository = new CustomerRepositoryImpl();

        // Inject the repository into the service using constructor injection
        CustomerService service = new CustomerService(repository);

        // Use the service to find a customer
        Customer customer = service.getCustomerById(1);

        // Display the customer details
        System.out.println(customer);
    }
}

