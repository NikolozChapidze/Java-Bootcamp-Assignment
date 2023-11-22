package dev.omedia.controller;

import dev.omedia.domain.Person;
import dev.omedia.repository.GenericRepository;
import dev.omedia.repository.PersonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("people")
public class PersonController extends GenericController<Person> {
    public PersonController(PersonRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<Person>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "person", key = "#id")
    public ResponseEntity<Person> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "person", key = "#updated.id")
    public ResponseEntity<Person> update(Person updated) {
        return super.update(updated);
    }

    @Override
    public ResponseEntity<Person> create(Person created) {
        return super.create(created);
    }

    @Override
    @CacheEvict(value = "person", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
