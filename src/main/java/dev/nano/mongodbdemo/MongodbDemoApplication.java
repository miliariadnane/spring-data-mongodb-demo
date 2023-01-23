package dev.nano.mongodbdemo;

import dev.nano.mongodbdemo.customer.Customer;
import dev.nano.mongodbdemo.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongodbDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodbDemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
			Customer customer = Customer.builder()
					.name("John Doe")
					.email("j.doe@gmail.com")
					.phone("1234567890")
					.build();
			customerRepository.insert(customer);
		};
	}
}
