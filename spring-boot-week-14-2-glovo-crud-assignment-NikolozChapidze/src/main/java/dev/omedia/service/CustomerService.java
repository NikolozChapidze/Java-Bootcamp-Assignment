package dev.omedia.service;

import dev.omedia.model.Courier;
import dev.omedia.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    Set<Customer> customers = new HashSet<>();

    public Collection<Customer> getCustomers(int page, int pageSize) {
        return this.customers.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Customer> getCustomer(long id) {
        return this.customers.stream()
                .filter(customer -> customer.getId() == id)
                .findAny();
    }

    public boolean addCustomer(final Customer customer) {
        long maxId = this.customers.stream()
                .map(Customer::getId)
                .max(Long::compare)
                .orElse(0L);
        customer.setId(maxId + 1);
        return this.customers.add(customer);
    }

    public boolean updateCustomer(final long id, final Customer customer) {
        this.removeCustomer(id);
        customer.setId(id);
        return this.addCustomer(customer);
    }

    public Optional<Customer> removeCustomer(final long id) {
        Optional<Customer> currCustomer = this.customers.stream()
                .filter(customer -> customer.getId() == id)
                .findAny();
        currCustomer.ifPresent(this.customers::remove);
        return currCustomer;
    }
}
