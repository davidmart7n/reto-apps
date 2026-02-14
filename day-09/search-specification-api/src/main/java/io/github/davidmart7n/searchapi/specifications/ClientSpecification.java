package io.github.davidmart7n.searchapi.specifications;

import org.springframework.data.jpa.domain.Specification;

import io.github.davidmart7n.searchapi.domain.CorporateClient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.*;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class ClientSpecification implements Specification<CorporateClient>{

    private final SearchCriteria criteria;

    public ClientSpecification(SearchCriteria criteria){
        this.criteria=criteria;
    }

@Override
    public Predicate toPredicate(Root<CorporateClient> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        // Accedemos dinámicamente al campo (ej: "companyName")
        Path<Object> path = root.get(criteria.getKey());

        return switch (criteria.getOperation()) {
            case EQUAL -> builder.equal(path, criteria.getValue());
            
            case LIKE -> builder.like(
                builder.lower(path.as(String.class)), 
                "%" + criteria.getValue().toString().toLowerCase() + "%"
            );
            
            case GREATER_THAN -> builder.greaterThan(
                path.as(Double.class), 
                Double.valueOf(criteria.getValue().toString())
            );
            
            case LESS_THAN -> builder.lessThan(
                path.as(Double.class), 
                Double.valueOf(criteria.getValue().toString())
            );
            
            default -> throw new IllegalArgumentException("Operation not supported");
        };
    }
}