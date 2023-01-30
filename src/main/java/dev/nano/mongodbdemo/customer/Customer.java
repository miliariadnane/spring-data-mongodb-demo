package dev.nano.mongodbdemo.customer;

import dev.nano.mongodbdemo.address.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "customers")
@Data @AllArgsConstructor
@Builder
public class Customer {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal salary;
    private double height;
    @DBRef(lazy = true, db = "addresses")
    private List<Address> addresses;
}
