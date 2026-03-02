package com.josepereyra.portfolio.about.domain.model;

import java.util.Objects;

public class AboutMe {

    private Long id;
    private final String biography;
    private final String professionalTitle;
    private final String education;
    private final String profileImageUrl;

    public AboutMe(Long id, String biography, String professionalTitle, String education, String profileImageUrl) {

        if (biography == null || biography.trim().isEmpty()) {
            throw new IllegalArgumentException("la biografia no puede ser nula o vacia"); 
        }

        if (professionalTitle == null || professionalTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("el titulo profesional no puede ser nulo o vacia");
        }

        this.id = id;
        this.biography = biography.trim();
        this.professionalTitle = professionalTitle.trim();
        this.education = education != null ? education.trim() : null;
        this.profileImageUrl = profileImageUrl != null ? profileImageUrl.trim() : null;
    }

    public Long getId() {
        return id;
    }

    public String getBiography() {
        return biography;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public String getEducation() {
        return education;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AboutMe aboutMe = (AboutMe) o;
            return Objects.equals(id, aboutMe.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }


}
