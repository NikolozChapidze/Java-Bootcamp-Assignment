package dev.omedia.service;

import dev.omedia.model.Product;
import dev.omedia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Collection<Product> getProducts(int page, int pageSize) {
        return repository.getProducts(page, pageSize);
    }

    public Optional<Product> getProduct(long id) {
        return repository.getProduct(id);
    }

    public void addProduct(final Product product) {
        repository.addProduct(product);
    }

    public Product updateProduct(final long id, final Product product) {
        return repository.updateProduct(id, product);
    }

    public int removeProduct(final long id) {
        return repository.removeProduct(id);
    }
}
