package dev.omedia.service;

import dev.omedia.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {
    Set<Product> products = new HashSet<>();

    public Collection<Product> getProducts(int page, int pageSize) {
        return this.products.stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toUnmodifiableList());
    }

    public Optional<Product> getProduct(long id) {
        return this.products.stream()
                .filter(product -> product.getId() == id)
                .findAny();
    }

    public boolean addProduct(final Product product) {
        long maxId = this.products.stream()
                .map(Product::getId)
                .max(Long::compare)
                .orElse(0L);
        product.setId(maxId + 1);
        return this.products.add(product);
    }

    public boolean updateProduct(final long id, final Product product) {
        this.removeProduct(id);
        product.setId(id);
        return this.addProduct(product);
    }

    public Optional<Product> removeProduct(final long id) {
        Optional<Product> currProduct = this.products.stream()
                .filter(product -> product.getId() == id)
                .findAny();
        currProduct.ifPresent(this.products::remove);
        return currProduct;
    }

    public void removeRestaurantProducts(final long id){
        this.products.stream()
                .filter(prod -> prod.getRestaurantId() == id)
                .forEach(products::remove);
    }

    public boolean existsProducts(Set<Long> keySet) {
        for (final var product : keySet) {
            if(!products.contains(product)){
                return false;
            }
        }
        return true;
    }
}
