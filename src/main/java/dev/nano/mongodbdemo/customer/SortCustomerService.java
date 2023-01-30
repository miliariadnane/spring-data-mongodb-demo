package dev.nano.mongodbdemo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SortCustomerService {
    private final MongoTemplate mongoTemplate;

    public List<Customer> sortByField(String fieldName) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, fieldName));
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        return customers;
    }

    public List<Customer> sortAndPageByField(String fieldName, int page, int size) {
        Query query = new Query();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, fieldName));
        query.with(pageable);
        List<Customer> customers = mongoTemplate.find(query, Customer.class);
        return customers;
    }
}
