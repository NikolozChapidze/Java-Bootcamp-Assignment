package dev.omedia.service;

import dev.omedia.model.Customer;
import dev.omedia.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Collection<Customer> getCustomers(int page, int pageSize) {
        return repository.getCustomers(page, pageSize);
    }

    public Optional<Customer> getCustomer(long id) {
        return repository.getCustomer(id);
    }

    public void addCustomer(final Customer customer) {
        repository.addCustomer(customer);
    }

    public Customer updateCustomer(final long id, final Customer customer) {
        return repository.updateCustomer(id, customer);

    }

    public int removeCustomer(final long id) {
        return repository.removeCustomer(id);
    }
}
