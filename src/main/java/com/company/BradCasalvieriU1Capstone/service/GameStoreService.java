package com.company.BradCasalvieriU1Capstone.service;

import com.company.BradCasalvieriU1Capstone.dao.*;
import com.company.BradCasalvieriU1Capstone.exception.NotFoundException;
import com.company.BradCasalvieriU1Capstone.model.*;
import com.company.BradCasalvieriU1Capstone.viewmodel.ConsoleViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.GameViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.InvoiceViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameStoreService {
    GameDao gameDao;
    ConsoleDao consoleDao;
    TShirtDao tShirtDao;
    SalesTaxRateDao salesTaxRateDao;
    ProcessingFeeDao processingFeeDao;
    InvoiceDao invoiceDao;

    @Autowired
    public GameStoreService(GameDao gameDao, ConsoleDao consoleDao, TShirtDao tShirtDao, SalesTaxRateDao salesTaxRateDao, ProcessingFeeDao processingFeeDao, InvoiceDao invoiceDao) {
        this.gameDao = gameDao;
        this.consoleDao = consoleDao;
        this.tShirtDao = tShirtDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.processingFeeDao = processingFeeDao;
        this.invoiceDao = invoiceDao;
    }

    // **********
    // GAME API
    // **********

    public GameViewModel saveGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setStudio(gameViewModel.getStudio());
        game.setPrice(gameViewModel.getPrice());
        game.setQuantity(gameViewModel.getQuantity());
        game = gameDao.addGame(game);

        gameViewModel.setId(game.getId());
        return gameViewModel;
    }

    public GameViewModel findGameById(int id) {
        Game game = gameDao.getGameById(id);
        if (game == null) return null;
        else return buildGameViewModel(game);
    }

    public List<GameViewModel> findAllGames() {
        List<Game> gameList = gameDao.getAllGames();
        if (gameList == null) return null;
        else {
            return gameList.stream()
                    .map(this::buildGameViewModel)
                    .collect(Collectors.toList());
        }
    }

    public List<GameViewModel> findGamesByTitle(String title) {
        List<Game> gameList = gameDao.getGameByTitle(title);
        if (gameList == null) return null;
        else {
            return gameList.stream()
                    .map(this::buildGameViewModel)
                    .collect(Collectors.toList());
        }
    }

    public List<GameViewModel> findGamesByStudio(String studio) {
        List<Game> gameList = gameDao.getGamesByStudio(studio);
        if (gameList == null) return null;
        else {
            return gameList.stream()
                    .map(this::buildGameViewModel)
                    .collect(Collectors.toList());
        }
    }

    public List<GameViewModel> findGamesByRating(String rating) {
        List<Game> gameList = gameDao.getGamesByRating(rating);
        if (gameList == null) return null;
        else {
            return gameList.stream()
                    .map(this::buildGameViewModel)
                    .collect(Collectors.toList());
        }
    }

    public void updateGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setId(gameViewModel.getId());
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setStudio(gameViewModel.getStudio());
        game.setPrice(gameViewModel.getPrice());
        game.setQuantity(gameViewModel.getQuantity());
        gameDao.updateGame(game);
    }

    public void removeGame(int id) {
        gameDao.deleteGame(id);
    }

    // **********
    // CONSOLE API
    // **********

    public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());
        console = consoleDao.addConsole(console);

        consoleViewModel.setId(console.getId());
        return consoleViewModel;
    }

    public ConsoleViewModel findConsoleById(int id) {
        Console console = consoleDao.getConsoleById(id);
        if (console == null) return null;
        else return buildConsoleViewModel(console);
    }

    public List<ConsoleViewModel> findAllConsoles() {
        List<Console> consoleList = consoleDao.getAllConsoles();
        if (consoleList == null) return null;
        else {
            return consoleList.stream()
                    .map(this::buildConsoleViewModel)
                    .collect(Collectors.toList());
        }
    }

    public List<ConsoleViewModel> findConsolesByManufacturer(String manufacturer) {
        List<Console> consoleList = consoleDao.getConsolesByManufacturer(manufacturer);
        if (consoleList == null) return null;
        else {
            return consoleList.stream()
                    .map(this::buildConsoleViewModel)
                    .collect(Collectors.toList());
        }
    }

    public void updateConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setId(consoleViewModel.getId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());
        consoleDao.updateConsole(console);
    }

    public void removeConsole(int id) {
        consoleDao.deleteConsole(id);
    }

    // **********
    // T SHIRT API
    // **********

    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirt = tShirtDao.addTShirt(tShirt);

        tShirtViewModel.setId(tShirt.getId());
        return tShirtViewModel;
    }

    public TShirtViewModel findTShirtById(int id) {
        TShirt tShirt = tShirtDao.getTShirtById(id);
        if (tShirt == null) return null;
        else return buildTShirtViewModel(tShirt);
    }

    public List<TShirtViewModel> findAllTShirts() {
        List<TShirt> tShirtList = tShirtDao.getAllTShirts();
        if (tShirtList == null) return null;
        else {
            return tShirtList.stream()
                    .map(this::buildTShirtViewModel)
                    .collect(Collectors.toList());
        }
    }

    public List<TShirtViewModel> findTShirtsBySize(String size) {
        List<TShirt> tShirtList = tShirtDao.getTShirtsBySize(size);
        if (tShirtList == null) return null;
        else {
            return tShirtList.stream()
                    .map(this::buildTShirtViewModel)
                    .collect(Collectors.toList());
        }
    }

    public List<TShirtViewModel> findTShirtsByColor(String color) {
        List<TShirt> tShirtList = tShirtDao.getTShirtsByColor(color);
        if (tShirtList == null) return null;
        else {
            return tShirtList.stream()
                    .map(this::buildTShirtViewModel)
                    .collect(Collectors.toList());
        }
    }

    public void updateTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.setId(tShirtViewModel.getId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirtDao.updateTShirt(tShirt);
    }

    public void removeTShirt(int id) {
        tShirtDao.deleteTShirt(id);
    }

    // **********
    // INVOICE API
    // **********
    
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipcode(invoiceViewModel.getZipcode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setQuantity(invoiceViewModel.getQuantity());

        // GET ITEM AND SET UNIT PRICE
        String itemType = invoiceViewModel.getItemType();
        int itemId = invoiceViewModel.getItemId();
        int quantity = invoiceViewModel.getQuantity();
        switch (itemType) {
            case "Console":
                Console console = consoleDao.getConsoleById(itemId);
                if (console == null) throw new NotFoundException("No console found with id " + itemId);
                if (console.getQuantity() < quantity) throw new IllegalArgumentException("Sorry, but there's not enough quantity in stock.");
                else {
                    invoice.setUnitPrice(console.getPrice());
                    console.setQuantity(console.getQuantity() - quantity);
                    consoleDao.updateConsole(console);
                }
                break;
            case "Game":
                Game game = gameDao.getGameById(itemId);
                if (game == null) throw new NotFoundException("No game found with id " + itemId);
                if (game.getQuantity() < quantity) throw new IllegalArgumentException("Sorry, but there's not enough quantity in stock.");
                else {
                    invoice.setUnitPrice(game.getPrice());
                    game.setQuantity(game.getQuantity() - quantity);
                    gameDao.updateGame(game);
                }
                break;
            case "T-Shirt":
                TShirt tshirt = tShirtDao.getTShirtById(itemId);
                if (tshirt == null) throw new NotFoundException("No t-shirt found with id " + itemId);
                if (tshirt.getQuantity() < quantity) throw new IllegalArgumentException("Sorry, but there's not enough quantity in stock.");
                else {
                    invoice.setUnitPrice(tshirt.getPrice());
                    tshirt.setQuantity(tshirt.getQuantity() - quantity);
                    tShirtDao.updateTShirt(tshirt);
                }
                break;
            default:
                throw new NotFoundException("No items found of type " + itemType);
        }

        // CALCULATE AND SET SUBTOTAL
        BigDecimal subtotal = invoice.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal subtotalFormatted = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
        invoice.setSubtotal(subtotalFormatted);

        // GET TAX RATE, CALCULATE AND SET TAX
        BigDecimal taxRate = salesTaxRateDao.getSalesTaxRateByState(invoiceViewModel.getState()).getRate();
        BigDecimal tax = subtotal.multiply(taxRate);
        BigDecimal taxFormatted = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
        invoice.setTax(taxFormatted);

        // GET AND SET PROCESSING FEE
        BigDecimal fee = processingFeeDao.getProcessingFeeByType(itemType).getFee();
        if (quantity > 10) fee = fee.add(new BigDecimal("15.49"));
        invoice.setProcessingFee(fee);

        // CALCULATE AND SET TOTAL
        BigDecimal total = subtotal.add(tax).add(fee);
        BigDecimal totalFormatted = total.setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal maxTotal = new BigDecimal("999.99");
        if (totalFormatted.compareTo(maxTotal) > 0) throw new IllegalArgumentException("The total must be under $1000.");
        else invoice.setTotal(totalFormatted);

        invoice = invoiceDao.addInvoice(invoice);
        return buildInvoiceViewModel(invoice);
    }

    public InvoiceViewModel findInvoiceById(int id) {
        Invoice invoice = invoiceDao.getInvoiceById(id);
        if (invoice == null) return null;
        else return buildInvoiceViewModel(invoice);
    }

    public List<InvoiceViewModel> findAllInvoices() {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        if (invoiceList == null) return null;
        else {
            return invoiceList.stream()
                    .map(this::buildInvoiceViewModel)
                    .collect(Collectors.toList());
        }
    }

    public void removeInvoice(int id) {
        invoiceDao.deleteInvoice(id);
    }

    // **********
    // SALES TAX API
    // **********

    public SalesTaxRate findSalesTaxRateByState(String state) {
        return salesTaxRateDao.getSalesTaxRateByState(state);
    }

    // **********
    // PROCESSING FEE API
    // **********

    public ProcessingFee findProcessingFeeByType(String type) {
        return processingFeeDao.getProcessingFeeByType(type);
    }

    // **********
    // HELPER METHODS
    // **********

    private GameViewModel buildGameViewModel(Game game) {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setId(game.getId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }

    private ConsoleViewModel buildConsoleViewModel(Console console) {
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setId(console.getId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());

        return consoleViewModel;
    }
    
    private TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.setId(tShirt.getId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return tShirtViewModel;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(invoice.getId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipcode(invoice.getZipcode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        return invoiceViewModel;
    }
}
