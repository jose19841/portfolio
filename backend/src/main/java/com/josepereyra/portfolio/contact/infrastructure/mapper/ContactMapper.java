package com.josepereyra.portfolio.contact.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.contact.domain.model.Contact;
import com.josepereyra.portfolio.contact.domain.model.Email;
import com.josepereyra.portfolio.contact.infrastructure.persistence.entity.ContactEntity;

@Component
public class ContactMapper {
    public ContactEntity toEntity(Contact contact) {
        ContactEntity entity = new ContactEntity();
        entity.setId(contact.getId());
        entity.setName(contact.getName());
        entity.setEmail(contact.getEmail().getValue());
        entity.setSubject(contact.getSubject());
        entity.setMessage(contact.getMessage());
        entity.setRead(contact.isRead());
        entity.setCreatedAt(contact.getCreatedAt());
        entity.setReadAt(contact.getReadAt());
        return entity;
    }
    public Contact toDomain(ContactEntity entity) {
        Email email = new Email(entity.getEmail());

        Contact contact = new Contact(
            entity.getId(),
            entity.getName(),
            email,
            entity.getSubject(),
            entity.getMessage()
        );
        return contact;
    }
}
