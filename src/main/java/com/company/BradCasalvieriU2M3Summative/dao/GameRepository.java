package com.company.BradCasalvieriU2M3Summative.dao;

import com.company.BradCasalvieriU2M3Summative.dto.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByStudio(String studio);
    List<Game> findByRating(String rating);
    List<Game> findByTitle(String title);
}
