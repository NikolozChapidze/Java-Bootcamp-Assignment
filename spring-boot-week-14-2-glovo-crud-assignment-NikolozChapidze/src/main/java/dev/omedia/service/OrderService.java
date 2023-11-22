package dev.omedia.service;

import dev.omedia.model.Order;
import dev.omedia.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    Set<Order> orders = new HashSet<>();

    public Collection<Order> getOrders(int page, int pageSize) {
        return this.orders.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Order> getOrder(long id) {
        return this.orders.stream()
                .filter(order -> order.getId() == id)
                .findAny();
    }

    public boolean addOrder(final Order order) {
        long maxId = this.orders.stream()
                .map(Order::getId)
                .max(Long::compare)
                .orElse(0L);
        order.setId(maxId + 1);
        return this.orders.add(order);
    }

    public boolean updateOrder(final long id, final Order order) {
        this.removeOrder(id);
        order.setId(id);
        return this.addOrder(order);
    }

    public boolean addProductToOrder(final long id, final long product){
        Optional<Order> order = getOrder(id);
        if(order.isEmpty()){
            return false;
        }
        Map<Long, Integer> products = order.get().getOrderProducts();
        products.put(product,products.getOrDefault(product,0)+1);
        return true;
    }

    public boolean removeProductFromOrder(final long id, final long product){
        Optional<Order> order = getOrder(id);
        if(order.isEmpty()){
            return false;
        }
        Map<Long, Integer> products = order.get().getOrderProducts();
        products.put(product,products.getOrDefault(product,1)-1);
        if (products.get(product) == 0){
            products.remove(product);
        }
        return true;
    }

    public Optional<Order> removeOrder(final long id) {
        Optional<Order> currOrder = this.orders.stream()
                .filter(order -> order.getId() == id)
                .findAny();
        currOrder.ifPresent(this.orders::remove);
        return currOrder;
    }
}
