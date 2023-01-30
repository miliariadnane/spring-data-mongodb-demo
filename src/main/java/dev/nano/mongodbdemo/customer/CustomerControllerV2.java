package dev.nano.mongodbdemo.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2/customers")
@RequiredArgsConstructor
public class CustomerControllerV2 {
    private final QueryMethodService queryMethodService;

    @GetMapping("/find/name")
    public ResponseEntity<List<Customer>> searchCustomerByName(@RequestParam String name) {
        return ResponseEntity.ok(
                queryMethodService.findAllCustomersByName(name)
        );
    }

    @GetMapping("/find/nameWithAddresses")
    public ResponseEntity<List<Customer>> searchCustomerByNameOrderByAddresses(
                @RequestParam String name,
                @RequestParam int page,
                @RequestParam int size
    ) {
        return ResponseEntity.ok(
                queryMethodService.findAllCustomersByNameOrderByAddresses(name, page, size)
        );
    }
}
