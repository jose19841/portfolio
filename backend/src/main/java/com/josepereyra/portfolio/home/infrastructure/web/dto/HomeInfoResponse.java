package com.josepereyra.portfolio.home.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeInfoResponse {
    private Long id;
    private String fullName;
    private String title;
    private String tagline;
    private String email;
    private String githubUrl;
    private String linkedinUrl;

}
