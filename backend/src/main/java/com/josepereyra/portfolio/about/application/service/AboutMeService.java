package com.josepereyra.portfolio.about.application.service;

import org.springframework.stereotype.Service;

import com.josepereyra.portfolio.about.application.port.in.GetAboutMeUseCase;
import com.josepereyra.portfolio.about.application.port.out.FindAboutMePort;
import com.josepereyra.portfolio.about.domain.exception.AboutMeNotFoundException;
import com.josepereyra.portfolio.about.domain.model.AboutMe;

@Service
public class AboutMeService implements GetAboutMeUseCase {

    private final FindAboutMePort findAboutMePort;

    public AboutMeService(FindAboutMePort findAboutMePort) {
        this.findAboutMePort = findAboutMePort;
    }

    @Override
    public AboutMe execute() {
        return findAboutMePort.findAboutMe()
        .orElseThrow(() -> new AboutMeNotFoundException("No se encontro informacion Sobre Mi"));
    }
}
