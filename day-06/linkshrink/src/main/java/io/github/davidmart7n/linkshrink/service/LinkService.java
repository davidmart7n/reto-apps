package io.github.davidmart7n.linkshrink.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.davidmart7n.linkshrink.model.Link;
import io.github.davidmart7n.linkshrink.model.dto.LinkDTO;
import io.github.davidmart7n.linkshrink.repository.LinkRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class LinkService {
    private final LinkRepository repository;

    public LinkService(LinkRepository repository) {
        this.repository = repository;
    }

    public LinkDTO createShortLink(String originalUrl) {
        String shortCode = generateShortCode();
        Link entity = Link.builder()
                .shortCode(shortCode)
                .originalUrl(originalUrl)
                .clicks(0L)
                .build();
        Link saved = repository.save(entity);
        System.out.println("\nOriginal URL"+saved.getOriginalUrl()+"\n");
        return mapToDto(saved);
    }

    public Page<LinkDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::mapToDto);
    }

    public Link findByShortCode(String shortCode) {
        return repository.findByShortCode(shortCode)
                .orElseThrow(() -> new EntityNotFoundException("Short code not found"));
    }

    private String generateShortCode() {
        return "abc" + System.currentTimeMillis() % 1000; 
    }

    private LinkDTO mapToDto(Link entity) {
        return new LinkDTO(entity.getId(), entity.getShortCode(), 
                          "http://localhost:8085/s/" + entity.getShortCode(), 
                          entity.getOriginalUrl(), entity.getClicks());
    }

    public void saveLink(Link link) {
    repository.save(link);
}

}
