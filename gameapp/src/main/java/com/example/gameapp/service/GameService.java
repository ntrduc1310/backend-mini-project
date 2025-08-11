package com.example.gameapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.gameapp.dto.GameDto;

public interface GameService {
    Page<GameDto> listGames(String category, String keyword, Pageable pageable);
    GameDto getGame(String id);
    GameDto saveOrUpdate(GameDto dto);
    void deleteGames(List<String> ids);
}

