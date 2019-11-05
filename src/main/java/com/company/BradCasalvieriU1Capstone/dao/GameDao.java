package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.Game;

import java.util.List;

public interface GameDao {
    Game addGame(Game game);
    Game getGameById(int id);
    List<Game> getAllGames();
    void updateGame(Game game);
    void deleteGame(int id);
    List<Game> getGamesByStudio(String studio);
    List<Game> getGamesByRating(String rating);
    List<Game> getGameByTitle(String title);
}
