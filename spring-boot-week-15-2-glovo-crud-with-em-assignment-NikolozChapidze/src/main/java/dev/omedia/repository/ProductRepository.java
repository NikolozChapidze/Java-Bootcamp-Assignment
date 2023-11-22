package dev.omedia.repository;

import dev.omedia.exception.ResourceNotFoundException;
import dev.omedia.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
@Transactional
@Slf4j
public class ProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Collection<Product> getProducts(@Value("${page}") int page, @Value("${pageSize}") int pageSize) {
        log.debug("Invoked getting product");
        return entityManager.createQuery("SELECT p FROM Product p ", Product.class)
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public Optional<Product> getProduct(long id) {
        log.debug("Invoked getting product with id : {}", id);
        return entityManager.createQuery("SELECT p FROM Product p WHERE p.productId=:id", Product.class)
                .setParameter("id", id)
                .getResultList().stream().findFirst();

    }

    public void addProduct(final Product product) {
        log.debug("Invoked adding product");
        entityManager.persist(product);
    }

    public Product updateProduct(final long id, final Product product) {
        log.debug("Invoked updating product with id {}", id);
        if (getProduct(id).isEmpty()) {
            throw new ResourceNotFoundException(id);
        }
        product.setProductId(id);
        return entityManager.merge(product);
    }

    public int removeProduct(final long id) {
        log.debug("Invoked removing product with id {}", id);
        return entityManager.createQuery("DELETE FROM Product p WHERE p.productId=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
