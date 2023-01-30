package dev.nano.mongodbdemo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    private final SearchCustomerService SearchCustomerService;

    @PostMapping
    public ResponseEntity<String> saveCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.saveCustomer(customer));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search/is")
    public ResponseEntity<List<Customer>> searchCustomerByName(@RequestParam String name) {
        return ResponseEntity.ok(SearchCustomerService.searchCustomerByName(name));
    }

    @GetMapping("/search/startWith")
    public ResponseEntity<List<Customer>> searchCustomerByNameStartingWith(@RequestParam String name) {
        return ResponseEntity.ok(SearchCustomerService.searchCustomerByNameStartingWith(name));
    }

    @GetMapping("/search/startWithAndContainM")
    public ResponseEntity<List<Customer>> searchCustomerByNameStartingWithAndContainMLetter(@RequestParam String name) {
        return ResponseEntity.ok(SearchCustomerService.searchCustomerByNameStartingWithAndContainMLetter(name));
    }

    @GetMapping("/search/startWithAndSalaryGreaterThan")
    public ResponseEntity<List<Customer>> searchCustomerByNameStartingWithAndSalaryGreaterThan
            (@RequestParam String name, @RequestParam String salary) {
        return ResponseEntity.ok(
                SearchCustomerService.searchCustomerByNameStartingWithAndSalaryGreaterThan(
                        name, new BigDecimal(salary)
                ));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCustomerById(@PathVariable String customerId) {
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.accepted().build();
    }
}
