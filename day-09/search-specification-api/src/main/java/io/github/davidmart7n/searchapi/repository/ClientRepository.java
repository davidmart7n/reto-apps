package io.github.davidmart7n.searchapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import io.github.davidmart7n.searchapi.domain.CorporateClient;

@Repository
public interface ClientRepository extends JpaRepository<CorporateClient,Long>,
                                         JpaSpecificationExecutor<CorporateClient>{
    

}
