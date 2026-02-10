package io.github.davidmart7n.watchlaterback.domain.dto;

import io.github.davidmart7n.watchlaterback.domain.Media;

public class MediaMapper {

    private Media entity;
    private MediaDTO dto;

    public static Media toEntity(MediaDTO dto) {
        return Media.builder()
                .title(dto.title())
                .type(dto.type())
                .duration(dto.duration())
                .build();
    }

    public static MediaDTO toDTO(Media entity) {
        return new MediaDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getType(),
                entity.getDuration());
    }
}
