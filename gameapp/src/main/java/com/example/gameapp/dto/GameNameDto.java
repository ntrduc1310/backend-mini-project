package com.example.gameapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.gameapp.model.Language;

public class GameNameDto {
    @NotNull
    private Language language;

    @NotBlank
    private String value;

    // Getters & Setters
    public Language getLanguage() { return language; }
    public void setLanguage(Language language) { this.language = language; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}