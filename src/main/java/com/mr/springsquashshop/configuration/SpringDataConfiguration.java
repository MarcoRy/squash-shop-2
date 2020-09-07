package com.mr.springsquashshop.configuration;

import com.mr.springsquashshop.entity.Product;
import com.mr.springsquashshop.entity.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.ExposureConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class SpringDataConfiguration implements RepositoryRestConfigurer {

    @Autowired
    EntityManager entityManager;


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration restConfig) {
        ExposureConfiguration config = restConfig.getExposureConfiguration();

        config.forDomainType(Product.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE));

        config.forDomainType(ProductCategory.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.PATCH, HttpMethod.PUT, HttpMethod.DELETE));

        exposeId(restConfig);
    }

    private void exposeId(RepositoryRestConfiguration restConfig) {

        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
        List<Class> classList = new ArrayList<>();

        for (EntityType entityType : entityTypes) {
            classList.add(entityType.getJavaType());
        }

        Class[] domainObjects = classList.toArray(new Class[0]);
        restConfig.exposeIdsFor(domainObjects);
    }
}
