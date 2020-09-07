package com.mr.springsquashshop.repository;

import com.mr.springsquashshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestController
public interface ProductRepository extends CrudRepository<Product, Long> {

    Page<Product> findByCategoryId(@RequestParam(name = "id") Long id, Pageable pageable);

    Page<Product> findProductByNameContains(@RequestParam(name = "name") String name, Pageable pageable);

}
