package DependencyInjectionExample;

//CustomerRepository.java
interface CustomerRepository {
 Customer findCustomerById(String id);
}

//CustomerRepositoryImpl.java
 class CustomerRepositoryImpl implements CustomerRepository {
 @Override
 public Customer findCustomerById(String id) {
     // For demonstration purposes, return a dummy customer
     return new Customer(id, "John Doe");
 }
}

//Customer.java
 class Customer {
 private String id;
 private String name;

 public Customer(String id, String name) {
     this.id = id;
     this.name = name;
 }

 public String getId() {
     return id;
 }

 public String getName() {
     return name;
 }
}

//CustomerService.java
class CustomerService {
 private CustomerRepository customerRepository;

 // Constructor for dependency injection
 public CustomerService(CustomerRepository customerRepository) {
     this.customerRepository = customerRepository;
 }

 public Customer getCustomerById(String id) {
     return customerRepository.findCustomerById(id);
 }
}

//DependencyInjectionExample.java
public class DependencyInjectionExample {
 public static void main(String[] args) {
     // Create a CustomerRepository implementation
     CustomerRepository customerRepository = new CustomerRepositoryImpl();

     // Inject the repository into the service
     CustomerService customerService = new CustomerService(customerRepository);

     // Use the service to find a customer
     Customer customer = customerService.getCustomerById("1");
     System.out.println("Customer ID: " + customer.getId());
     System.out.println("Customer Name: " + customer.getName());
 }
}
