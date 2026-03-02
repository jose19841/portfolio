package com.josepereyra.portfolio.contact.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.josepereyra.portfolio.contact.infrastructure.persistence.entity.ContactEntity;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactEntity, Long> {

}
