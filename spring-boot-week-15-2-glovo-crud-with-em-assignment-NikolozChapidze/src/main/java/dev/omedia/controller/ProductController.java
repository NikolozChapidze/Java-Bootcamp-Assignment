package dev.omedia.controller;

import dev.omedia.model.Product;
import dev.omedia.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("products")
@Slf4j
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<Product> getProducts(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return productService.getProducts(page, pageSize);
    }

    @GetMapping("{identifier}")
    public Product getProduct(@PathVariable(name = "identifier") long id) {
        Optional<Product> sportOptional = productService.getProduct(id);
        return sportOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public int removeProduct(@PathVariable long id) {
        return productService.removeProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product sport) {
        productService.addProduct(sport);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product sport) {
        return productService.updateProduct(id, sport);
    }
}
