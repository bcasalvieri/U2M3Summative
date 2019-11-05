package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.Console;
import com.company.BradCasalvieriU1Capstone.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoTest {
    @Autowired
    GameDao gameDao;

    private Game game;
    private Game game2;
    private Game game3;

    @Before
    public void setUp() throws Exception {
        clearDatabase();
        setUpTestObjects();
    }

    public void clearDatabase() {
        gameDao.getAllGames().forEach(game1 -> gameDao.deleteGame(game1.getId()));
    }

    public void setUpTestObjects() {
        game = new Game();
        game.setTitle("New Game");
        game.setEsrbRating("G");
        game.setDescription("Newest game on market.");
        game.setPrice(new BigDecimal("59.99"));
        game.setStudio("Studio");
        game.setQuantity(100);

        game2 = new Game();
        game2.setTitle("New game2");
        game2.setEsrbRating("G");
        game2.setDescription("Newest game on market.");
        game2.setPrice(new BigDecimal("69.99"));
        game2.setStudio("Studio");
        game2.setQuantity(100);

        game3 = new Game();
        game3.setTitle("New game3");
        game3.setEsrbRating("M");
        game3.setDescription("Newest game3 on market.");
        game3.setPrice(new BigDecimal("29.99"));
        game3.setStudio("Other studio");
        game3.setQuantity(25);
    }

    @Test
    public void shouldAddGetDeleteGame() {
        game = gameDao.addGame(game);

        Game game1 = gameDao.getGameById(game.getId());
        assertEquals(game, game1);

        gameDao.deleteGame(game.getId());
        game1 = gameDao.getGameById(game.getId());
        assertNull(game1);
    }

    @Test
    public void shouldGetAllGames() {
        gameDao.addGame(game);
        gameDao.addGame(game2);
        gameDao.addGame(game3);

        List<Game> games = gameDao.getAllGames();
        assertEquals(3, games.size());
    }

    @Test
    public void shouldUpdateGame() {
        game = gameDao.addGame(game);
        game.setTitle("UPDATED TITLE");
        game.setEsrbRating("UPDATED ESRB RATING");
        game.setDescription("UPDATE DESCRIPTION");
        game.setPrice(new BigDecimal("1.99"));
        game.setStudio("UPDATED STUDIO");
        game.setQuantity(1);
        gameDao.updateGame(game);

        Game game1 = gameDao.getGameById(game.getId());
        assertEquals(game, game1);
    }

    @Test
    public void shouldGetGamesByStudio() {
        gameDao.addGame(game);
        gameDao.addGame(game2);
        gameDao.addGame(game3);

        List<Game> games = gameDao.getGamesByStudio("Studio");
        assertEquals(2, games.size());

        List<Game> games1 = gameDao.getGamesByStudio("Other studio");
        assertEquals(1, games1.size());
    }

    @Test
    public void getGamesByRating() {
        gameDao.addGame(game);
        gameDao.addGame(game2);
        gameDao.addGame(game3);

        List<Game> games = gameDao.getGamesByRating("G");
        assertEquals(2, games.size());

        List<Game> games1 = gameDao.getGamesByRating("M");
        assertEquals(1, games1.size());
    }

    @Test
    public void getGameByTitle() {
        game = gameDao.addGame(game);
        List<Game> gamesList = new ArrayList<>();
        gamesList.add(game);

        List<Game> games = gameDao.getGameByTitle("New Game");
        assertEquals(gamesList, games);
    }
}