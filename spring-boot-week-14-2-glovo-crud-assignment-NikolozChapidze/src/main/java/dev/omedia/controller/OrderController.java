package dev.omedia.controller;

import dev.omedia.exceprion.ResourceNotFoundException;
import dev.omedia.model.Order;
import dev.omedia.service.OrderService;
import dev.omedia.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("orders")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public Collection<Order> getOrders(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return orderService.getOrders(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Order getOrder(@PathVariable(name = "identifier") long id) {
        Optional<Order> orderOptional = orderService.getOrder(id);
        return orderOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public Order removeOrder(@PathVariable long id) {
        return orderService.removeOrder(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addOrder(@RequestBody Order order) {
        if (!(productService.existsProducts(order.getOrderProducts().keySet()))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not all products found");
        }
        return orderService.addOrder(order);
    }

    @PutMapping("{id}")
    public boolean updateOrder(@PathVariable long id, @RequestBody Order order) {
        if (!(productService.existsProducts(order.getOrderProducts().keySet()))) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not all products found");
        }
        return orderService.updateOrder(id, order);
    }

}
