package com.josepereyra.portfolio.contact.application.service;

import org.springframework.stereotype.Service;
import com.josepereyra.portfolio.contact.application.ports.in.CreateContactUseCase;
import com.josepereyra.portfolio.contact.application.ports.out.SaveContactPort;
import com.josepereyra.portfolio.contact.domain.model.Contact;
import com.josepereyra.portfolio.contact.domain.model.Email;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.CreateContactCommand;

@Service
public class ContactService implements CreateContactUseCase {

    private final SaveContactPort saveContactPort;

    public ContactService(SaveContactPort saveContactPort) {
        this.saveContactPort = saveContactPort;
    }
    

    @Override
    public Contact execute(CreateContactCommand command) {
    
        Email email = new Email(command.getEmail());

        Contact contact = new Contact(
            null,
             command.getName(),
              email,
               command.getSubject(),
             command.getMessage()
            );
            return saveContactPort.save(contact);
        
    }

    

}
