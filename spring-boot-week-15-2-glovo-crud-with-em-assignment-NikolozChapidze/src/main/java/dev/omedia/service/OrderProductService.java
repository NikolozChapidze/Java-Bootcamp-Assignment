package dev.omedia.service;

import dev.omedia.model.OrderProduct;
import dev.omedia.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderProductService {
    private final OrderProductRepository repository;

    @Autowired
    public OrderProductService(OrderProductRepository repository) {
        this.repository = repository;
    }

    public Collection<OrderProduct> getOrderProducts(int page, int pageSize) {
        return repository.getOrderProducts(page, pageSize);
    }

    public Optional<OrderProduct> getOrderProduct(long id) {
        return repository.getOrderProduct(id);
    }

    public void addOrderProduct(final OrderProduct orderProduct) {
        repository.addOrderProduct(orderProduct);
    }

    public OrderProduct updateOrderProduct(final long id, final OrderProduct orderProduct) {
        return repository.updateOrderProduct(id, orderProduct);
    }

    public int removeOrderProduct(final long id) {
        return repository.removeOrderProduct(id);
    }
}
