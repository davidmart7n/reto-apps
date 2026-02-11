package io.github.davidmart7n.linkshrink.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.davidmart7n.linkshrink.model.Link;
import io.github.davidmart7n.linkshrink.service.LinkService;


@RestController
public class RedirectController {
    private final LinkService service;

    public RedirectController(LinkService service) {
        this.service = service;
    }

    @GetMapping("/s/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        Link link = service.findByShortCode(shortCode);
        link.setClicks(link.getClicks() + 1);
        service.saveLink(link); // Actualiza clicks

        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                .location(URI.create(link.getOriginalUrl()))
                .build();
    }
}
