package dev.omedia.service;

import dev.omedia.model.Order;
import dev.omedia.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Collection<Order> getOrders(int page, int pageSize) {
        return repository.getOrders(page, pageSize);
    }

    public Optional<Order> getOrder(long id) {
        return repository.getOrder(id);
    }

    public void addOrder(final Order order) {
        repository.addOrder(order);
    }

    public Order updateOrder(final long id, final Order order) {
        return repository.updateOrder(id, order);
    }

    public int removeOrder(final long id) {
        return repository.removeOrder(id);
    }
}
