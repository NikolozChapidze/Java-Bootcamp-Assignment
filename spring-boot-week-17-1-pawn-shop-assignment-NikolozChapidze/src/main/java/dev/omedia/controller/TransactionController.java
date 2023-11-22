package dev.omedia.controller;

import dev.omedia.domain.Transaction;
import dev.omedia.repository.GenericRepository;
import dev.omedia.repository.TransactionRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transactions")
public class TransactionController extends GenericController<Transaction> {
    public TransactionController(TransactionRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<Transaction>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "transaction", key = "#id")
    public ResponseEntity<Transaction> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "transaction", key = "#updated.id")
    public ResponseEntity<Transaction> update(Transaction updated) {
        return super.update(updated);
    }

    @Override
    public ResponseEntity<Transaction> create(Transaction created) {
        return super.create(created);
    }

    @Override
    @CacheEvict(value = "transaction", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
