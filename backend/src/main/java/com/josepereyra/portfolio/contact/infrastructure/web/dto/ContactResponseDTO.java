package com.josepereyra.portfolio.contact.infrastructure.web.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private boolean isRead;
    private LocalDateTime createdAt;


}
