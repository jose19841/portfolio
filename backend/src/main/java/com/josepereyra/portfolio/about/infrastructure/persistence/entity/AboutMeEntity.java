package com.josepereyra.portfolio.about.infrastructure.persistence.entity;

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
@Table(name = "about_me")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutMeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String biography;

    @Column(nullable = false, length = 100)
    private String professionalTitle;

    @Column(columnDefinition = "TEXT")
    private String education;

    @Column(length = 255)
    private String profileImageUrl;

}
