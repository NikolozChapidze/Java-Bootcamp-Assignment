package dev.omedia.controller;

import dev.omedia.domain.gadget.Company;
import dev.omedia.repository.CompanyRepository;
import dev.omedia.repository.GenericRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gadgets/companies")
public class CompanyController extends GenericController<Company>{
    public CompanyController(CompanyRepository repository) {
        super(repository);
    }

    @Override
    public ResponseEntity<Page<Company>> getPage(Pageable pageable) {
        return super.getPage(pageable);
    }

    @Override
    @Cacheable(value = "company", key = "#id")
    public ResponseEntity<Company> getOne(Long id) {
        return super.getOne(id);
    }

    @Override
    @CachePut(value = "company", key = "#updated.id")
    public ResponseEntity<Company> update(Company updated) {
        return super.update(updated);
    }

    @Override
    public ResponseEntity<Company> create(Company created) {
        return super.create(created);
    }

    @Override
    @CacheEvict(value = "company", key = "#id")
    public ResponseEntity<String> delete(Long id) {
        return super.delete(id);
    }
}
