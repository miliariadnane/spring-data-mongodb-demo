package dev.nano.mongodbdemo.customer;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerRepository
        extends MongoRepository<Customer, String> {

    List<Customer> findAllByName(String name);
    List<Customer> findAllByNameIgnoreCase(String name);
    List<Customer> findAllByNameStartingWith(String name);
   Customer findCustomerByNameContaining(String name);
   Customer findCustomerBySalaryGreaterThan(BigDecimal salary);
   List<Customer> findCustomerByHeightBetween(BigDecimal min, BigDecimal max);
   List<Customer> findCustomerByNameOrderByAddresses(String name, Pageable pageable);

   /* query method using @Query annotation */
    @Query("{ 'name' : ?0 }") // ?0 is the first parameter
    List<Customer> findCustomerByName(String name);

    @Query("{ 'name' : ?0, 'salary' : ?1 }")
    List<Customer> findCustomerByNameAndSalary(String name, BigDecimal salary);
}
