package com.josepereyra.portfolio.home.application.ports.out;

import java.util.Optional;

import com.josepereyra.portfolio.home.domain.model.HomeInfo;

public interface FindHomeInfoPort {
    Optional<HomeInfo> findHomeInfo();

}
