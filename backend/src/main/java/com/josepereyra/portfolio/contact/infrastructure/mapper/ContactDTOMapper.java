package com.josepereyra.portfolio.contact.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.contact.domain.model.Contact;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.ContactRequestDTO;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.ContactResponseDTO;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.CreateContactCommand;

@Component
public class ContactDTOMapper {
    public CreateContactCommand toCommand(ContactRequestDTO requestDTO) {
        return new CreateContactCommand(
            requestDTO.getName(),
            requestDTO.getEmail(),
            requestDTO.getSubject(),
            requestDTO.getMessage()
        );
    }
    public ContactResponseDTO toResponseDTO(Contact contact) {
        return new ContactResponseDTO(
            contact.getId(),
            contact.getName(),
            contact.getEmail().getValue(),
            contact.getSubject(),
            contact.getMessage(),
            contact.isRead(),
            contact.getCreatedAt()
        );
    }
}
