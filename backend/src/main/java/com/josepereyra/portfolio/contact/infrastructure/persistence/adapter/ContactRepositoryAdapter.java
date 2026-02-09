package com.josepereyra.portfolio.contact.infrastructure.persistence.adapter;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.contact.application.ports.out.SaveContactPort;
import com.josepereyra.portfolio.contact.domain.model.Contact;
import com.josepereyra.portfolio.contact.infrastructure.mapper.ContactMapper;
import com.josepereyra.portfolio.contact.infrastructure.persistence.entity.ContactEntity;
import com.josepereyra.portfolio.contact.infrastructure.persistence.repository.ContactJpaRepository;

@Component
public class ContactRepositoryAdapter implements SaveContactPort {

    private final ContactJpaRepository jpaRepository;
    private final ContactMapper mapper;

    public ContactRepositoryAdapter(ContactJpaRepository jpaRepository, ContactMapper mapper) { 
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Contact save(Contact contact) {
        ContactEntity entity = mapper.toEntity(contact);
        ContactEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

}
