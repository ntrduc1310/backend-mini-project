package com.example.gameapp.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.gameapp.dto.GameDto;
import com.example.gameapp.exception.ResourceNotFoundException;
import com.example.gameapp.model.Category;
import com.example.gameapp.model.Game;
import com.example.gameapp.repository.GameRepository; 
@Service
public class GameServiceImpl implements GameService {
    @Autowired private GameRepository repo;
    @Autowired private ModelMapper mapper;

    @Override
    public Page<GameDto> listGames(String category, String keyword, Pageable pageable) {
        Category cat = (category != null && !category.isEmpty())
            ? Category.valueOf(category.toUpperCase())
            : null;
        Page<Game> page = (cat != null)
            ? repo.findByCategoryAndNamesValueContainingIgnoreCase(cat, keyword == null ? "" : keyword, pageable)
            : repo.findByNamesValueContainingIgnoreCase(keyword == null ? "" : keyword, pageable);
        return page.map(g -> mapper.map(g, GameDto.class));
    }

    @Override
    public GameDto getGame(String id) {
        Game g = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Game not found: " + id));
        return mapper.map(g, GameDto.class);
    }

    @Override
    public GameDto saveOrUpdate(GameDto dto) {
        Game g = mapper.map(dto, Game.class);
        if (g.getNames() != null) {
            g.getNames().forEach(n -> n.setGame(g));
        }
        return mapper.map(repo.save(g), GameDto.class);
    }

    @Override
    public void deleteGames(List<String> ids) {
        List<Game> list = repo.findAllById(ids); // ✅ sửa var → List<Game>
        if (list.isEmpty()) throw new ResourceNotFoundException("No games to delete");
        repo.deleteAll(list);
    }
}