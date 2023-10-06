package com.anis.project.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anis.project.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);

}
