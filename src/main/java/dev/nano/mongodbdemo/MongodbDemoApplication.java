package dev.nano.mongodbdemo;

import dev.nano.mongodbdemo.address.Address;
import dev.nano.mongodbdemo.address.AddressRepository;
import dev.nano.mongodbdemo.customer.Customer;
import dev.nano.mongodbdemo.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MongodbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CustomerRepository customerRepository,
			AddressRepository addressRepository
	) {
		return args -> {
			Customer customer = Customer.builder()
					.name("John Doe")
					.email("j.doe@gmail.com")
					.phone("1234567890")
					.build();
			customerRepository.insert(customer);

			Address address1 = Address.builder()
					.city("New York")
					.street("Wall Street")
					.zipCode("12345")
					.build();

			Address address2 = Address.builder()
					.city("Los Angeles")
					.street("Hollywood Blvd")
					.zipCode("54321")
					.build();

			addressRepository.insert(List.of(address1, address2));
		};
	}
}
