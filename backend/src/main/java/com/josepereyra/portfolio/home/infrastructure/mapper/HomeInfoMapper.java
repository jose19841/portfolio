package com.josepereyra.portfolio.home.infrastructure.mapper;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.home.domain.model.HomeInfo;
import com.josepereyra.portfolio.home.infrastructure.persistence.entity.HomeInfoEntity;

@Component
public class HomeInfoMapper {

    public HomeInfo toDomain(HomeInfoEntity entity) {
        return new HomeInfo(
            entity.getId(),
            entity.getFullName(),
            entity.getTitle(),
            entity.getTagline(),
            entity.getEmail(),
            entity.getGithubUrl(),
            entity.getLinkedinUrl()
        );
    }
}
