package dev.omedia.controller;

import dev.omedia.domain.Shop;
import dev.omedia.repository.GenericRepository;
import dev.omedia.repository.ShopRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shops")
public class ShopController extends GenericController<Shop> {
    public ShopController(ShopRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<Shop>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "shop", key = "#id")
    public ResponseEntity<Shop> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "shop", key = "#updated.id")
    public ResponseEntity<Shop> update(Shop updated) {
        return super.update(updated);
    }

    @Override
    public ResponseEntity<Shop> create(Shop created) {
        return super.create(created);
    }

    @Override
    @CacheEvict(value = "shop", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
