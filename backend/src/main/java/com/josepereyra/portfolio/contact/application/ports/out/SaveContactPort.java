package com.josepereyra.portfolio.contact.application.ports.out;

import com.josepereyra.portfolio.contact.domain.model.Contact;

public interface SaveContactPort {

    Contact save(Contact contact);


}
