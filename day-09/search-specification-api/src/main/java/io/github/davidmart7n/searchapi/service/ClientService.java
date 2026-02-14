package io.github.davidmart7n.searchapi.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.github.davidmart7n.searchapi.domain.CorporateClient;
import io.github.davidmart7n.searchapi.repository.ClientRepository;
import io.github.davidmart7n.searchapi.specifications.*;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public List<CorporateClient> searchClients(List<SearchCriteria> criteriaList) {
        Specification<CorporateClient> finalSpec = Specification.where(null);

        for (SearchCriteria criteria : criteriaList) {
            finalSpec = finalSpec.and(new ClientSpecification(criteria));
        }

        return clientRepository.findAll(finalSpec);
    }
}
