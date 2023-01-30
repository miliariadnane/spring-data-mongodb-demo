package dev.nano.mongodbdemo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchCustomerService {
    private final MongoTemplate mongoTemplate;

    public List<Customer> searchCustomerByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        return customers;
    }

    public List<Customer> searchCustomerByNameStartingWith(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^" + name));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        return customers;
    }

    public List<Customer> searchCustomerByNameStartingWithAndContainMLetter(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^" + name).and("name").regex("m"));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        return customers;
    }

    public List<Customer> searchCustomerByNameStartingWithAndSalaryGreaterThan(String name, BigDecimal salary) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex("^" + name).and("salary").gt(salary));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        return customers;
    }
}
