package dev.omedia.controller;

import dev.omedia.exceprion.ResourceNotFoundException;
import dev.omedia.model.Customer;
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
        Optional<Product> productOptional = productService.getProduct(id);
        return productOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public Product removeProduct(@PathVariable long id) {
        return productService.removeProduct(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("{id}")
    public boolean updateProduct(@PathVariable long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
