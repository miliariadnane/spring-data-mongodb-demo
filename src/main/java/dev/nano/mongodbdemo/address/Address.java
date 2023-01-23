package dev.nano.mongodbdemo.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Data @AllArgsConstructor
@Builder
public class Address {
    @Id
    private String id;
    private String street;
    private String city;
    private String zipCode;
}
