package dev.nano.mongodbdemo.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
@Data @AllArgsConstructor
@Builder
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
}
