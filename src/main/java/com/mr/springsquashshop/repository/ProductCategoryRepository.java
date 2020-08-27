package com.mr.springsquashshop.repository;

import com.mr.springsquashshop.entity.ProductCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource(path = "categories", itemResourceRel = "categories")
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

}
