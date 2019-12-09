package com.company.BradCasalvieriU2M3Summative.dao;

import com.company.BradCasalvieriU2M3Summative.dto.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    GameRepository gameRepo;

    private Game game;

    @Before
    public void setUp() throws Exception {
        gameRepo.deleteAll();
        setUpTestObjects();
    }

    public void setUpTestObjects() {
        game = new Game();
        game.setTitle("Madden 2019");
        game.setRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setPrice(new BigDecimal("69.99"));
        game.setStudio("EA Sports");
        game.setQuantity(100);
    }

    @Test
    public void findByStudio() {
        gameRepo.save(game);
        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        List<Game> gameList1 = gameRepo.findByStudio("EA Sports");
        assertEquals(gameList, gameList1);
    }

    @Test
    public void findByRating() {
        gameRepo.save(game);
        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        List<Game> gameList1 = gameRepo.findByRating("G");
        assertEquals(gameList, gameList1);
    }

    @Test
    public void findByTitle() {
        gameRepo.save(game);
        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        List<Game> gameList1 = gameRepo.findByTitle("Madden 2019");
        assertEquals(gameList, gameList1);
    }
}