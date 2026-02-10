package io.github.davidmart7n.watchlaterback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.davidmart7n.watchlaterback.domain.Media;
import java.util.List;
import java.util.Optional;


@Repository
public interface MediaRepository extends JpaRepository<Media,Long>{
    
    public Optional<Media> findByTitle(String title);
}
