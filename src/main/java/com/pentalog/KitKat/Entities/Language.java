package com.pentalog.KitKat.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer languageId;
    @Column(unique = true)
    private String languageName;

    public Language() {
    }

    public Language(Integer languageId, String languageName) {
        this.languageId = languageId;
        this.languageName = languageName;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}
