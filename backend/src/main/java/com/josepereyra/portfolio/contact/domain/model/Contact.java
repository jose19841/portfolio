package com.josepereyra.portfolio.contact.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Contact {

    private Long id;
    private final String name;
    private final Email email;
    private final String subject;
    private final String message;
    private boolean isRead;
    private final LocalDateTime createdAt;
    private LocalDateTime readAt;

    public Contact(Long id, String name, Email email, String subject, String message) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("el nombre del contacto no puede ser nulo o vacio");
        }
        if (message == null || message.trim().length() < 10) {
            throw new IllegalArgumentException("el mensaje debe tener al menos 10 caracteres");
        }
        this.id = id;
        this.name = name.trim();
        this.email = email;
        this.subject = subject != null ? subject.trim() : null;
        this.message = message.trim();
        this.isRead = false;
        this.createdAt = LocalDateTime.now();
        this.readAt = null;
    }

    public void markAsRead() {
        this.isRead = true;
        this.readAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
   
}
