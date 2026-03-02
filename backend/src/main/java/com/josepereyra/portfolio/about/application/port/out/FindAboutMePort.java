package com.josepereyra.portfolio.about.application.port.out;

import java.util.Optional;

import com.josepereyra.portfolio.about.domain.model.AboutMe;

public interface FindAboutMePort {
    Optional<AboutMe> findAboutMe();

}
