package com.example.gameapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gameapp.dto.GameDto;
import com.example.gameapp.service.GameService;

@RestController
@RequestMapping("/api/v1/games") 
public class GameRestController {

    private final GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    // List + filter + pagination
    @GetMapping
    public Page<GameDto> list(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return gameService.listGames(category, keyword, pageable);
    }

    // Get by id
    @GetMapping("/{id}")
    public GameDto get(@PathVariable String id) {
        return gameService.getGame(id);
    }

    // Create
    @PostMapping
    public ResponseEntity<GameDto> create(@Valid @RequestBody GameDto dto) {
        GameDto saved = gameService.saveOrUpdate(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Update
    @PutMapping("/{id}")
    public GameDto update(@PathVariable String id, @Valid @RequestBody GameDto dto) {
        dto.setId(id);
        return gameService.saveOrUpdate(dto);
    }

    // Delete multiple
    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody List<String> ids) {
        gameService.deleteGames(ids);
        return ResponseEntity.ok().build();
    }
}
