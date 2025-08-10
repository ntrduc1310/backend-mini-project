package com.example.gameapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "redirect:/games";
    }

    @GetMapping("/games")
    public String gameList() {
        return "game-list";
    }

    @GetMapping("/games/new")
    public String gameRegistration() {
        return "game-registration";
    }

    @GetMapping("/games/edit/{id}")
    public String editGame() {
        return "game-registration";
    }
}
