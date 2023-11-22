package dev.omedia.controller;

import dev.omedia.model.OrderProduct;
import dev.omedia.service.OrderProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("orderProducts")
@Slf4j
public class OrderProductController {
    private final OrderProductService orderProductService;

    @Autowired
    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping
    public Collection<OrderProduct> getOrderProducts(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return orderProductService.getOrderProducts(page, pageSize);
    }


    @GetMapping("{identifier}")
    public OrderProduct getOrderProduct(@PathVariable(name = "identifier") long id) {
        Optional<OrderProduct> sportOptional = orderProductService.getOrderProduct(id);
        return sportOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "OrderProduct Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public int removeOrderProduct(@PathVariable long id) {
        return orderProductService.removeOrderProduct(id);
    }

    @PostMapping
    public void addOrderProduct(@RequestBody OrderProduct sport) {
        orderProductService.addOrderProduct(sport);
    }

    @PutMapping("{id}")
    public OrderProduct updateOrderProduct(@PathVariable long id, @RequestBody OrderProduct sport) {
        return orderProductService.updateOrderProduct(id, sport);
    }
}
