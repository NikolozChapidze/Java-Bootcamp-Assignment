package dev.omedia.controller;

import dev.omedia.model.Order;
import dev.omedia.service.OrderService;
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

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Collection<Order> getOrders(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return orderService.getOrders(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Order getOrder(@PathVariable(name = "identifier") long id) {
        Optional<Order> sportOptional = orderService.getOrder(id);
        return sportOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public int removeOrder(@PathVariable long id) {
        return orderService.removeOrder(id);
    }

    @PostMapping
    public void addOrder(@RequestBody Order sport) {
        orderService.addOrder(sport);
    }

    @PutMapping("{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order sport) {
        return orderService.updateOrder(id, sport);
    }
}
