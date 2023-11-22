package dev.omedia.service;

import dev.omedia.domain.GenericEntity;
import dev.omedia.exception.NotFoundException;
import dev.omedia.repository.GenericRepository;
import org.hibernate.annotations.NotFound;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;


public abstract class GenericService<T extends GenericEntity<T>>{
    private final GenericRepository<T> repository;

    protected GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public T get(Long id){
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("Resource not found with %d id",id))
        );
    }

    @Transactional
    public T update(T updated){
        T dbDomain = get(updated.getId());
        dbDomain.update(updated);

        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        return repository.save(newDomain);
    }

    @Transactional
    public void delete(Long id){
        get(id);
        repository.deleteById(id);
    }
}

