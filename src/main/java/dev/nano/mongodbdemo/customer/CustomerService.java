package dev.nano.mongodbdemo.customer;

import java.util.List;

public interface CustomerService {
    public String saveCustomer(Customer customer);
    public Customer getCustomerById(String id);
    public List<Customer> getAllCustomers();
    public void deleteCustomerById(String id);
}
