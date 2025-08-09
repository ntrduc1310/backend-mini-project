package com.example.gameapp.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.gameapp.model.Category;

public class GameDto {
    @NotBlank
    private String id;

    @NotNull
    private Category category;

    @NotNull
    private List<GameNameDto> names;

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<GameNameDto> getNames() { return names; }
    public void setNames(List<GameNameDto> names) { this.names = names; }
}