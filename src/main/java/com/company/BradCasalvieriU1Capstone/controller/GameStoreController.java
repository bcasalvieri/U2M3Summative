package com.company.BradCasalvieriU1Capstone.controller;

import com.company.BradCasalvieriU1Capstone.exception.NotFoundException;
import com.company.BradCasalvieriU1Capstone.service.GameStoreService;
import com.company.BradCasalvieriU1Capstone.viewmodel.ConsoleViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.GameViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.InvoiceViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameStoreController {
    @Autowired
    GameStoreService gameStoreService;

    // **********
    // GAME ROUTES
    // **********

    @PostMapping(value = "/games")
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel game) {
        return gameStoreService.saveGame(game);
    }

    @GetMapping(value = "/games")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {
        List<GameViewModel> games = gameStoreService.findAllGames();
        if (games != null && games.size() == 0) throw new NotFoundException("There are no games.");
        return games;
    }

    @GetMapping(value = "/games/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByTitle(@PathVariable("title") String title) {
        List<GameViewModel> games = gameStoreService.findGamesByTitle(title);
        if (games != null && games.size() == 0) throw new NotFoundException("No games found for " + title);
        return games;
    }

    @GetMapping(value = "/games/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByStudio(@PathVariable("studio") String studio) {
        List<GameViewModel> games = gameStoreService.findGamesByStudio(studio);
        if (games != null && games.size() == 0) throw new NotFoundException("No games found for " + studio);
        return games;
    }

    @GetMapping(value = "/games/rating/{rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getGamesByRating(@PathVariable("rating") String rating) {
        List<GameViewModel> games = gameStoreService.findGamesByRating(rating);
        if (games != null && games.size() == 0) throw new NotFoundException("No games found for rating " + rating);
        return games;
    }

    @GetMapping(value = "/game/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int id) {
        GameViewModel game = gameStoreService.findGameById(id);
        if (game == null) throw new NotFoundException("No game found for id " + id);
        return game;
    }

    @PutMapping(value = "/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@PathVariable("id") int id, @RequestBody @Valid GameViewModel game) {
        if (game.getId() == 0) game.setId(id);
        if (id != game.getId()) throw new IllegalArgumentException("Game ID on path must match the ID in the Game object.");
        gameStoreService.updateGame(game);
    }

    @DeleteMapping(value = "/game/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable int id) {
        gameStoreService.removeGame(id);
    }

    // **********
    // CONSOLE ROUTES
    // **********

    @PostMapping(value = "/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsoles(@RequestBody @Valid ConsoleViewModel console) {
        return gameStoreService.saveConsole(console);
    }

    @GetMapping(value = "/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles() {
        List<ConsoleViewModel> consoles = gameStoreService.findAllConsoles();
        if (consoles != null && consoles.size() == 0) throw new NotFoundException("There are no consoles.");
        return consoles;
    }

    @GetMapping(value = "/consoles/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getConsolesByManufacturer(@PathVariable("manufacturer") String manufacturer) {
        List<ConsoleViewModel> consoles = gameStoreService.findConsolesByManufacturer(manufacturer);
        if (consoles != null && consoles.size() == 0) throw new NotFoundException("No consoles found for " + manufacturer);
        return consoles;
    }

    @GetMapping(value = "/console/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int id) {
        ConsoleViewModel console = gameStoreService.findConsoleById(id);
        if (console == null) throw new NotFoundException("No console found for id " + id);
        return console;
    }

    @PutMapping(value = "/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@PathVariable("id") int id, @RequestBody @Valid ConsoleViewModel console) {
        if (console.getId() == 0) console.setId(id);
        if (id != console.getId()) throw new IllegalArgumentException("Console ID on path must match the ID in the console object.");
        gameStoreService.updateConsole(console);
    }

    @DeleteMapping(value = "/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        gameStoreService.removeConsole(id);
    }

    // **********
    // T SHIRT ROUTES
    // **********

    @PostMapping(value = "/tshirts")
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tshirt) {
        return gameStoreService.saveTShirt(tshirt);
    }

    @GetMapping(value = "/tshirts")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirts() {
        List<TShirtViewModel> tshirts = gameStoreService.findAllTShirts();
        if (tshirts != null && tshirts.size() == 0) throw new NotFoundException("There are no t-shirts.");
        return tshirts;
    }

    @GetMapping(value = "/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsBySize(@PathVariable("size") String size) {
        List<TShirtViewModel> tshirts = gameStoreService.findTShirtsBySize(size);
        if (tshirts != null && tshirts.size() == 0) throw new NotFoundException("No t-shirts found for size " + size);
        return tshirts;
    }

    @GetMapping(value = "/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getTShirtsByColor(@PathVariable("color") String color) {
        List<TShirtViewModel> tshirts = gameStoreService.findTShirtsByColor(color);
        if (tshirts != null && tshirts.size() == 0) throw new NotFoundException("No t-shirts found for color " + color);
        return tshirts;
    }

    @GetMapping(value = "/tshirt/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getTShirt(@PathVariable("id") int id) {
        TShirtViewModel tshirt = gameStoreService.findTShirtById(id);
        if (tshirt == null) throw new NotFoundException("No t-shirt found for id " + id);
        return tshirt;
    }

    @PutMapping(value = "/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@PathVariable("id") int id, @RequestBody @Valid TShirtViewModel tshirt) {
        if (tshirt.getId() == 0) tshirt.setId(id);
        if (id != tshirt.getId()) throw new IllegalArgumentException("T-shirt ID on path must match the ID in the t-shirt object.");
        gameStoreService.updateTShirt(tshirt);
    }

    @DeleteMapping(value = "/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable int id) {
        gameStoreService.removeTShirt(id);
    }

    // **********
    // INVOICE ROUTES
    // **********

    @PostMapping(value = "/invoices")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoice) {
        return gameStoreService.saveInvoice(invoice);
    }

    @GetMapping(value = "/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        List<InvoiceViewModel> invoices = gameStoreService.findAllInvoices();
        if (invoices != null && invoices.size() == 0) throw new NotFoundException("There are no invoices.");
        return invoices;
    }
}
