package io.github.davidmart7n.linkshrink.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.davidmart7n.linkshrink.model.Link;
import io.github.davidmart7n.linkshrink.model.dto.CreateLinkRequest;
import io.github.davidmart7n.linkshrink.model.dto.LinkDTO;
import io.github.davidmart7n.linkshrink.service.LinkService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("api/link")
public class LinkController {
    
    private final LinkService service;

    public LinkController(LinkService service){
        this.service=service;
    }
    @GetMapping("/short-links")
    public ResponseEntity<Page<LinkDTO>> getLinks(Pageable pageable){
        return ResponseEntity.ok(service.findAll(pageable)) ;
    }

    @PostMapping
    public ResponseEntity<LinkDTO> createShortLink(@RequestBody CreateLinkRequest request){
        LinkDTO dtoResult = service.createShortLink(request.url());
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoResult);
    }

    
}
