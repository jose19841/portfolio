package com.josepereyra.portfolio.home.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "home_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HomeInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 200)
    private String tagline;

    @Column(length = 100) 
    private String email;

    @Column(length = 200)
    private String githubUrl;

    @Column(length = 200)
    private String linkedinUrl;

}
