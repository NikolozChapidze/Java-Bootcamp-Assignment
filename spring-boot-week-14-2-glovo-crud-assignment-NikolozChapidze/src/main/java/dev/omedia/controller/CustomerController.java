package dev.omedia.controller;

import dev.omedia.exceprion.ResourceNotFoundException;
import dev.omedia.model.Courier;
import dev.omedia.model.Customer;
import dev.omedia.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("customers")
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public Collection<Customer> getCustomers(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return customerService.getCustomers(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Customer getCustomer(@PathVariable(name = "identifier") long id) {
        Optional<Customer> customerOptional = customerService.getCustomer(id);
        return customerOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public Customer removeCustomer(@PathVariable long id) {
        return customerService.removeCustomer(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping("{id}")
    public boolean updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
}
