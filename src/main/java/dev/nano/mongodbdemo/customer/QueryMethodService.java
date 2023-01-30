package dev.nano.mongodbdemo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryMethodService {
    private final CustomerRepository customerRepository;

    public List<Customer> findAllCustomersByName(String name) {
        return customerRepository.findAllByName(name);
    }

    public List<Customer> findAllCustomersByNameOrderByAddresses(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findCustomerByNameOrderByAddresses(name, PageRequest.of(page, size));
    }
}
