package com.josepereyra.portfolio.about.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josepereyra.portfolio.about.infrastructure.persistence.entity.AboutMeEntity;

@Repository
public interface AboutMeJpaRepository extends JpaRepository<AboutMeEntity, Long> {
    Optional<AboutMeEntity> findFirstByOrderByIdAsc();

}
