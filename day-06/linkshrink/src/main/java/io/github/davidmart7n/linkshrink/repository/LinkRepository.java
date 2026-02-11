package io.github.davidmart7n.linkshrink.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.davidmart7n.linkshrink.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link,Long>{
     
    public Optional<Link> findByShortCode(String shortCode);
}
