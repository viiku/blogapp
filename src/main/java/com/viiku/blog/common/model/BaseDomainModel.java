package com.viiku.blog.common.model;

import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Abstract class named {@link BaseDomainModel} for domain models,
 * providing common fields for creation and update timestamps.
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseDomainModel {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;
}