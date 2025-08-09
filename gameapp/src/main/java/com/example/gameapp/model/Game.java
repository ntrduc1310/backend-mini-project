package com.example.gameapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "games")
public class Game {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_id")
    private List<GameName> names;

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public List<GameName> getNames() { return names; }
    public void setNames(List<GameName> names) { this.names = names; }
}