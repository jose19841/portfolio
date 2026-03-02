package com.josepereyra.portfolio.about.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.about.application.port.out.FindAboutMePort;
import com.josepereyra.portfolio.about.domain.model.AboutMe;
import com.josepereyra.portfolio.about.infrastructure.mapper.AboutmeMapper;
import com.josepereyra.portfolio.about.infrastructure.persistence.repository.AboutMeJpaRepository;

@Component
public class AboutMeRespositoryAdapter implements FindAboutMePort {

    private final AboutMeJpaRepository jpaRepository;
    private final AboutmeMapper mapper;

    public AboutMeRespositoryAdapter(AboutMeJpaRepository jpaRepository, AboutmeMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<AboutMe> findAboutMe() {
        return jpaRepository.findFirstByOrderByIdAsc()
        .map(mapper::toDomain);
    }
}
