package com.josepereyra.portfolio.about.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.about.domain.model.AboutMe;
import com.josepereyra.portfolio.about.infrastructure.persistence.entity.AboutMeEntity;

@Component
public class AboutmeMapper {

    public AboutMe toDomain(AboutMeEntity entity) {
        return new AboutMe(
            entity.getId(),
            entity.getBiography(),
            entity.getProfessionalTitle(),
            entity.getEducation(),
            entity.getProfileImageUrl()
        );
    }
}
