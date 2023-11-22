package dev.omedia.controller;

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
        Optional<Customer> sportOptional = customerService.getCustomer(id);
        return sportOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public int removeCustomer(@PathVariable long id) {
        return customerService.removeCustomer(id);
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer sport) {
        customerService.addCustomer(sport);
    }

    @PutMapping("{id}")
    public Customer updateCustomer(@PathVariable long id, @RequestBody Customer sport) {
        return customerService.updateCustomer(id, sport);
    }
}
