package com.josepereyra.portfolio.home.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.home.domain.model.HomeInfo;
import com.josepereyra.portfolio.home.infrastructure.web.dto.HomeInfoResponse;

@Component
public class HomeInfoDtoMapper {
    public HomeInfoResponse toResponse(HomeInfo homeInfo) {
        return new HomeInfoResponse(
            homeInfo.getId(),
            homeInfo.getFullName(),
            homeInfo.getTitle(),
            homeInfo.getTagline(),
            homeInfo.getEmail(),
            homeInfo.getGithubUrl(),
            homeInfo.getLinkedinUrl()
        );
    }

}
