package com.viiku.blog.repositories;

import com.viiku.blog.domain.model.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {

    List<CategoryEntity> findAll();

    boolean existsByNameIgnoreCase(String name);
}
