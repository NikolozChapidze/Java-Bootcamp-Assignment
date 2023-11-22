package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
@Slf4j
public class CustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Customer> getCustomers(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting customer");
        return entityManager.createQuery("SELECT c FROM Customer c ", Customer.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Customer> getCustomer(long id) {
        log.debug("Invoked getting customer with id : {}", id);
        return entityManager.createQuery("SELECT c FROM Customer c WHERE c.id=:id", Customer.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void addCustomer(final Customer customer) {
        log.debug("Invoked adding customer");
        entityManager.persist(customer);
    }

    public Customer updateCustomer(final long id, final Customer customer) {
        log.debug("Invoked updating customer with id {}", id);
        if (getCustomer(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        customer.setId(id);
        return entityManager.merge(customer);
    }

    public int removeCustomer(final long id) {
        log.debug("Invoked removing customer with id {}", id);
        return entityManager.createQuery("DELETE FROM Customer c WHERE c.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
