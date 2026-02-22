package com.josepereyra.portfolio.about.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.about.domain.model.AboutMe;
import com.josepereyra.portfolio.about.infrastructure.web.dto.AboutMeResponse;

@Component
public class AboutMeDtoMapper {
    public AboutMeResponse toResponse(AboutMe aboutMe) {
        return new AboutMeResponse(
            aboutMe.getId(),
            aboutMe.getBiography(),
            aboutMe.getProfessionalTitle(),
            aboutMe.getEducation(),
            aboutMe.getProfileImageUrl()
        );
    }
}
