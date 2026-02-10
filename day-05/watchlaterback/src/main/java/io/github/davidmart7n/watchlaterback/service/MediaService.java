package io.github.davidmart7n.watchlaterback.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import io.github.davidmart7n.watchlaterback.domain.Media;
import io.github.davidmart7n.watchlaterback.domain.dto.MediaDTO;
import io.github.davidmart7n.watchlaterback.repository.MediaRepository;
import jakarta.persistence.EntityNotFoundException;
import io.github.davidmart7n.watchlaterback.domain.dto.MediaMapper;

@Service
public class MediaService {

    private final MediaRepository repository;

    public MediaService(MediaRepository repository) {
        this.repository = repository;
    }

    public Page<MediaDTO> findAllMedia(Pageable pageable) {
        Page<Media> page = repository.findAll(pageable);
        return page.map(MediaMapper::toDTO);
    }

    public MediaDTO findByTitle(String title) {
        Media entity = repository.findByTitle(title)
                .orElseThrow(() -> new EntityNotFoundException("Media not found"));
        return MediaMapper.toDTO(entity);
    }

    public MediaDTO saveNew(MediaDTO dto) {

        Media media = repository.save(MediaMapper.toEntity(dto));

        return MediaMapper.toDTO(media);
    }

    public MediaDTO update(MediaDTO dto, Long id) {

        Media media = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(""));
        media.setTitle(dto.title());
        media.setType(dto.type());
        media.setDuration(dto.duration());

        repository.save(media);

        return MediaMapper.toDTO(media);

    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
