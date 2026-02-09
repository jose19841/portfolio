package com.josepereyra.portfolio.contact.infrastructure.web.dto;

public class CreateContactCommand {

    private final String name;
    private final String email;
    private final String subject;
    private final String message;


    public CreateContactCommand(String name, String email, String subject, String message) {
        this.name = name;
        this.email = email;
        this.subject = subject;
        this.message = message;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getSubject() {
        return subject;
    }


    public String getMessage() {
        return message;
    }

    

    
}
