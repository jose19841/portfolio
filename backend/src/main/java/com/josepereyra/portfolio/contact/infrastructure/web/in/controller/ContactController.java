package com.josepereyra.portfolio.contact.infrastructure.web.in.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.josepereyra.portfolio.contact.application.ports.in.CreateContactUseCase;
import com.josepereyra.portfolio.contact.domain.model.Contact;
import com.josepereyra.portfolio.contact.infrastructure.mapper.ContactDTOMapper;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.ContactRequestDTO;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.ContactResponseDTO;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.CreateContactCommand;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final CreateContactUseCase createContactUseCase;
    private final ContactDTOMapper contactDTOMapper;

    public ContactController(CreateContactUseCase createContactUseCase, ContactDTOMapper contactDTOMapper) {
        this.createContactUseCase = createContactUseCase;
        this.contactDTOMapper = contactDTOMapper;
}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ContactResponseDTO createContact(@RequestBody @Valid ContactRequestDTO requestDTO) {
        CreateContactCommand command = contactDTOMapper.toCommand(requestDTO);
        Contact contact = createContactUseCase.execute(command);
        return contactDTOMapper.toResponseDTO(contact);
    }
}
