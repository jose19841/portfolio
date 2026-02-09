package com.josepereyra.portfolio.contact.infrastructure.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor  
@AllArgsConstructor
public class ContactRequestDTO {
    @NotBlank(message = "el nombre es requerido")
    @Size(max = 100, message = "el nombre no puede exceder los 100 caracteres")
    private String name;

    @NotBlank(message = "el email es requerido")
    @Email(message = "el formato del email no es valido")
    private String email;

    @Size(max = 200, message = "el asunto no puede exceder los 200 caracteres")
    private String subject;

    @NotBlank(message = "el mensaje es requerido")
    @Size(min = 10, message = "el mensaje debe tener al menos 10 caracteres")
    private String message;
    
}

