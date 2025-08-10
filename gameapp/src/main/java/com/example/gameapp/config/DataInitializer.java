package com.example.gameapp.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.gameapp.model.Category;
import com.example.gameapp.model.Game;
import com.example.gameapp.model.GameName;
import com.example.gameapp.model.Language;
import com.example.gameapp.repository.GameRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists
        if (gameRepository.count() > 0) {
            return;
        }

        // Create sample games
        createSampleGame("UNCHARTED4", Category.ADVENTURE, 
            new GameName[]{
                createGameName(Language.EN, "Uncharted 4"),
                createGameName(Language.KO, "언차티드 4"),
                createGameName(Language.JA, "アンチャーテッド 4")
            });

        createSampleGame("WITCHER3", Category.RPG,
            new GameName[]{
                createGameName(Language.EN, "The Witcher 3: Wild Hunt"),
                createGameName(Language.KO, "더 위처 3: 와일드 헌트"),
                createGameName(Language.JA, "ウィッチャー3 ワイルドハント")
            });

        createSampleGame("PORTAL2", Category.PUZZLE,
            new GameName[]{
                createGameName(Language.EN, "Portal 2"),
                createGameName(Language.KO, "포탈 2"),
                createGameName(Language.JA, "ポータル 2")
            });

        createSampleGame("DOOM2016", Category.ACTION,
            new GameName[]{
                createGameName(Language.EN, "DOOM"),
                createGameName(Language.KO, "둠"),
                createGameName(Language.JA, "DOOM")
            });
    }

    private void createSampleGame(String id, Category category, GameName[] names) {
        Game game = new Game();
        game.setId(id);
        game.setCategory(category);
        game.setNames(Arrays.asList(names));
        
        // Set game reference for each name
        for (GameName name : names) {
            name.setGame(game);
        }
        
        gameRepository.save(game);
    }

    private GameName createGameName(Language language, String value) {
        GameName gameName = new GameName();
        gameName.setLanguage(language);
        gameName.setValue(value);
        return gameName;
    }
}
