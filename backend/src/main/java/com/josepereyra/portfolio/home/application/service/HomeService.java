package com.josepereyra.portfolio.home.application.service;

import org.springframework.stereotype.Service;
import com.josepereyra.portfolio.home.application.ports.in.GetHomeInfoUseCase;
import com.josepereyra.portfolio.home.application.ports.out.FindHomeInfoPort;
import com.josepereyra.portfolio.home.domain.exception.HomeInfoNotFoundException;
import com.josepereyra.portfolio.home.domain.model.HomeInfo;

@Service
public class HomeService implements GetHomeInfoUseCase {

    private final FindHomeInfoPort findHomeInfoPort;

    public HomeService(FindHomeInfoPort findHomeInfoPort) {
        this.findHomeInfoPort = findHomeInfoPort;
    }

    @Override
    public HomeInfo execute() {
        return findHomeInfoPort.findHomeInfo()
        .orElseThrow(() -> new HomeInfoNotFoundException("Informacion de inicio no encontrada"));
    }
}
