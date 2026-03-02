package com.josepereyra.portfolio.home.domain.model;

import java.util.Objects;

public class HomeInfo {

    private Long id;
    private final String fullName;
    private final String title;
    private final String tagline;
    private final String email;
    private final String githubUrl;
    private final String linkedinUrl;

    public HomeInfo(Long id, String fullName, String title, String tagline, String email, String githubUrl, String linkedinUrl) {
        
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("el nombre completo no puede ser nulo o vacio");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("el titulo no puede ser nulo o vacio");
        }
        this.id = id;
        this.fullName = fullName.trim();
        this.title = title.trim();
        this.tagline = tagline != null ? tagline.trim() : null;
        this.email = email != null ? email.trim() : null;
        this.githubUrl = githubUrl != null ? githubUrl.trim() : null;
        this.linkedinUrl = linkedinUrl != null ? linkedinUrl.trim() : null;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTitle() {
        return title;
    }

    public String getTagline() {
        return tagline;
    }

    public String getEmail() {
        return email;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public String getLinkedinUrl() {
        return linkedinUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeInfo homeInfo = (HomeInfo) o;
        return Objects.equals(id, homeInfo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

