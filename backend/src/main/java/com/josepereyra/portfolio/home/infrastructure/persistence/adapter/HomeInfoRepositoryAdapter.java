package com.josepereyra.portfolio.home.infrastructure.persistence.adapter;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.josepereyra.portfolio.home.application.ports.out.FindHomeInfoPort;
import com.josepereyra.portfolio.home.domain.model.HomeInfo;
import com.josepereyra.portfolio.home.infrastructure.mapper.HomeInfoMapper;
import com.josepereyra.portfolio.home.infrastructure.persistence.repository.HomeInfoJpaRepository;

@Component
public class HomeInfoRepositoryAdapter implements FindHomeInfoPort {
    private final HomeInfoJpaRepository jpaRepository;
    private final HomeInfoMapper mapper;

    public HomeInfoRepositoryAdapter(HomeInfoJpaRepository jpaRepository, HomeInfoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }
    @Override
    public Optional<HomeInfo> findHomeInfo() {
        return jpaRepository.findFirstByOrderByIdAsc()
        .map(mapper::toDomain);
    }
}

