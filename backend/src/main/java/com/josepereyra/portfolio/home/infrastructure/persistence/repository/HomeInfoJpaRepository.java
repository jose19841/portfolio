package com.josepereyra.portfolio.home.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josepereyra.portfolio.home.infrastructure.persistence.entity.HomeInfoEntity;

@Repository
public interface HomeInfoJpaRepository extends JpaRepository<HomeInfoEntity, Long> {
    Optional<HomeInfoEntity> findFirstByOrderByIdAsc();

}
