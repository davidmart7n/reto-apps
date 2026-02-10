package io.github.davidmart7n.watchlaterback.controller;

import io.github.davidmart7n.watchlaterback.domain.Media;
import io.github.davidmart7n.watchlaterback.domain.dto.MediaDTO;
import io.github.davidmart7n.watchlaterback.service.MediaService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService service;

    public MediaController(MediaService service) {
        this.service = service;
    };

    @GetMapping
    public ResponseEntity<List<MediaDTO>> getAllMedia() {
        return ResponseEntity.ok(service.findAllMedia());
    }

    @GetMapping("/search")
    public ResponseEntity<MediaDTO> getMediaByTitle(@RequestParam String title) {
        return ResponseEntity.ok(service.findByTitle(title));
    }

    @PostMapping
    public ResponseEntity<MediaDTO> saveNewMedia(@Valid @RequestBody MediaDTO dto) {
        
        MediaDTO savedMedia = service.saveNew(dto);
        // Apuntes de esto + @VALID + RequestParam + ResponseEntity<Void>
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaDTO> updateMedia(@PathVariable Long id,
                                                @Valid @RequestBody MediaDTO dto) {
        return ResponseEntity.ok(service.update(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
