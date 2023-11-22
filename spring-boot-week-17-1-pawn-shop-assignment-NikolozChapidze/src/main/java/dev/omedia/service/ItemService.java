package dev.omedia.service;

import dev.omedia.domain.Item;
import dev.omedia.exception.NotFoundException;
import dev.omedia.repository.GenericRepository;
import dev.omedia.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;

public abstract class ItemService<T extends Item>{
    private final ItemRepository<T> repository;

    protected ItemService(ItemRepository<T> repository) {
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
        return repository.save(updated);
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
