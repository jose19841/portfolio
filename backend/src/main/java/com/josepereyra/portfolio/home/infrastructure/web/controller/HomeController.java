package com.josepereyra.portfolio.home.infrastructure.web.controller;

import org.springframework.web.bind.annotation.RestController;

import com.josepereyra.portfolio.home.application.ports.in.GetHomeInfoUseCase;
import com.josepereyra.portfolio.home.domain.model.HomeInfo;
import com.josepereyra.portfolio.home.infrastructure.mapper.HomeInfoDtoMapper;
import com.josepereyra.portfolio.home.infrastructure.web.dto.HomeInfoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/home")    
public class HomeController {
    private final GetHomeInfoUseCase getHomeInfoUseCase;
    private final HomeInfoDtoMapper mapper;
    public HomeController(GetHomeInfoUseCase getHomeInfoUseCase, HomeInfoDtoMapper mapper) { 
        this.getHomeInfoUseCase = getHomeInfoUseCase;
        this.mapper = mapper;
    }

    @GetMapping
   @ResponseStatus(HttpStatus.OK)
   public HomeInfoResponse getHomeInfo() {
    HomeInfo homeInfo = getHomeInfoUseCase.execute();
    return mapper.toResponse(homeInfo);
    }

}
