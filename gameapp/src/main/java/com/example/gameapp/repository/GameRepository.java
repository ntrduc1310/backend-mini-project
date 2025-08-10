package com.example.gameapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gameapp.model.Category;
import com.example.gameapp.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {
    Page<Game> findByCategoryAndNamesValueContainingIgnoreCase(
        Category category, String keyword, Pageable pageable);

    Page<Game> findByNamesValueContainingIgnoreCase(String keyword, Pageable pageable);
}