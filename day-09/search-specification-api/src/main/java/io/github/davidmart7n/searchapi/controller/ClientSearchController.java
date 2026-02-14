package io.github.davidmart7n.searchapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.davidmart7n.searchapi.domain.CorporateClient;
import io.github.davidmart7n.searchapi.service.ClientService;
import io.github.davidmart7n.searchapi.specifications.SearchCriteria;

@RestController
@RequestMapping
public class ClientSearchController {
    
    
    private ClientService service;

    public ClientSearchController(ClientService service){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<List<CorporateClient>> search(@RequestBody List<SearchCriteria> criteria){
        List<CorporateClient> results = service.searchClients(criteria);

        return results.isEmpty() 
                ? ResponseEntity.noContent().build() 
                : ResponseEntity.ok(results);
    }
}