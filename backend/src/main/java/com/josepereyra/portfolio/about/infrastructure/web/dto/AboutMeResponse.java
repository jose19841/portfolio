package com.josepereyra.portfolio.about.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutMeResponse {
    private Long id;
    private String biography;
    private String professionalTitle;
    private String education;
    private String profileImageUrl;

}
