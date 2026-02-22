package com.josepereyra.portfolio.about.infrastructure.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.josepereyra.portfolio.about.application.port.in.GetAboutMeUseCase;
import com.josepereyra.portfolio.about.domain.model.AboutMe;
import com.josepereyra.portfolio.about.infrastructure.mapper.AboutMeDtoMapper;
import com.josepereyra.portfolio.about.infrastructure.web.dto.AboutMeResponse;

@RestController
@RequestMapping("/api/about-me")
public class AboutMeController {

    private final GetAboutMeUseCase getAboutMeUseCase;
    private final AboutMeDtoMapper aboutMeDtoMapper;

    public AboutMeController(GetAboutMeUseCase getAboutMeUseCase, AboutMeDtoMapper aboutMeDtoMapper) {
        this.getAboutMeUseCase = getAboutMeUseCase;
        this.aboutMeDtoMapper = aboutMeDtoMapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public AboutMeResponse getAboutMe() {
        AboutMe aboutMe = getAboutMeUseCase.execute();
        return aboutMeDtoMapper.toResponse(aboutMe);
    }

}
