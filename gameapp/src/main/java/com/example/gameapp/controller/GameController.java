package com.example.gameapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/api/games")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping
	public ResponseEntity<Page<GameDto>> getAllGames(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "keyword", required = false) String keyword
	) {
		PageRequest pageable = PageRequest.of(page, size);
		Page<GameDto> result = gameService.listGames(category, keyword, pageable);
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GameDto> getGameById(@PathVariable String id) {
		return ResponseEntity.ok(gameService.getGame(id));
	}

	@PostMapping
	public ResponseEntity<GameDto> createGame(@RequestBody GameDto gameDto) {
		return ResponseEntity.ok(gameService.saveOrUpdate(gameDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<GameDto> updateGame(@PathVariable String id, @RequestBody GameDto gameDto) {
		gameDto.setId(id);
		return ResponseEntity.ok(gameService.saveOrUpdate(gameDto));
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteGames(@RequestBody List<String> ids) {
		gameService.deleteGames(ids);
		return ResponseEntity.ok().build();
	}
}
