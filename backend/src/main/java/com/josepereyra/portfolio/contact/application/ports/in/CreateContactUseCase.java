package com.josepereyra.portfolio.contact.application.ports.in;

import com.josepereyra.portfolio.contact.domain.model.Contact;
import com.josepereyra.portfolio.contact.infrastructure.web.dto.CreateContactCommand;
public interface CreateContactUseCase {

    Contact execute(CreateContactCommand command);
}
