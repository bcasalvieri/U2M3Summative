package com.company.BradCasalvieriU1Capstone.service;

import com.company.BradCasalvieriU1Capstone.dao.*;
import com.company.BradCasalvieriU1Capstone.dto.*;
import com.company.BradCasalvieriU1Capstone.viewmodel.ConsoleViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.GameViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.InvoiceViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.TShirtViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GameStoreServiceTest {
    GameDao gameDao;
    ConsoleDao consoleDao;
    TShirtDao tShirtDao;
    SalesTaxRateDao salesTaxRateDao;
    ProcessingFeeDao processingFeeDao;
    InvoiceDao invoiceDao;
    GameStoreService gameStoreService;

    @Before
    public void setUp() throws Exception {
        setUpGameDaoMock();
        setUpConsoleDaoMock();
        setUpTShirtDaoMock();
        setUpInvoiceDaoMock();
        setUpSalesTaxRateDaoMock();
        setUpProcessingFeeDaoMock();
        gameStoreService = new GameStoreService(gameDao, consoleDao, tShirtDao, salesTaxRateDao, processingFeeDao, invoiceDao);
    }
    
    // **********
    // GAME TESTS
    // **********

    @Test
    public void shouldSaveAndFindGame() {
        GameViewModel game = new GameViewModel();
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        game = gameStoreService.saveGame(game);
        GameViewModel fromService = gameStoreService.findGameById(game.getId());
        assertEquals(game, fromService);
    }

    @Test
    public void shouldFindAllGames() {
        GameViewModel game = new GameViewModel();
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        game = gameStoreService.saveGame(game);
        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);
        List<GameViewModel> fromService = gameStoreService.findAllGames();
        assertEquals(gameList, fromService);
    }

    @Test
    public void shouldFindGameByTitle() {
        GameViewModel game = new GameViewModel();
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        game = gameStoreService.saveGame(game);
        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);
        List<GameViewModel> fromService = gameStoreService.findGamesByTitle(game.getTitle());
        assertEquals(gameList, fromService);
    }

    @Test
    public void shouldFindGameByStudio() {
        GameViewModel game = new GameViewModel();
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        game = gameStoreService.saveGame(game);
        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);
        List<GameViewModel> fromService = gameStoreService.findGamesByStudio(game.getStudio());
        assertEquals(gameList, fromService);
    }

    @Test
    public void shouldFindGamesByRating() {
        GameViewModel game = new GameViewModel();
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        game = gameStoreService.saveGame(game);
        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);
        List<GameViewModel> fromService = gameStoreService.findGamesByRating(game.getEsrbRating());
        assertEquals(gameList, fromService);
    }
    
    // **********
    // CONSOLE TESTS
    // **********
    
    @Test
    public void shouldSaveAndFindConsole() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1TB");
        console.setProcessor("APU");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        console = gameStoreService.saveConsole(console);
        ConsoleViewModel fromService = gameStoreService.findConsoleById(console.getId());
        assertEquals(console, fromService);
    }

    @Test
    public void shouldFindAllConsoles() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1TB");
        console.setProcessor("APU");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        console = gameStoreService.saveConsole(console);
        List<ConsoleViewModel> consoleList = new ArrayList<>();
        consoleList.add(console);
        List<ConsoleViewModel> fromService = gameStoreService.findAllConsoles();
        assertEquals(consoleList, fromService);
    }

    @Test
    public void shouldFindConsolesByManufacturer() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1TB");
        console.setProcessor("APU");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        console = gameStoreService.saveConsole(console);
        List<ConsoleViewModel> consoleList = new ArrayList<>();
        consoleList.add(console);
        List<ConsoleViewModel> fromService = gameStoreService.findConsolesByManufacturer(console.getManufacturer());
        assertEquals(consoleList, fromService);
    }

    // **********
    // T SHIRT TESTS
    // **********

    @Test
    public void shouldSaveAndFindTShirt() {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.setSize("X-Large");
        tShirtViewModel.setColor("Red");
        tShirtViewModel.setDescription("Red shirt");
        tShirtViewModel.setPrice(new BigDecimal("25.99"));
        tShirtViewModel.setQuantity(20);

        tShirtViewModel = gameStoreService.saveTShirt(tShirtViewModel);
        TShirtViewModel fromService = gameStoreService.findTShirtById(tShirtViewModel.getId());
        assertEquals(tShirtViewModel, fromService);
    }

    @Test
    public void shouldFindAllTShirts() {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.setSize("X-Large");
        tShirtViewModel.setColor("Red");
        tShirtViewModel.setDescription("Red shirt");
        tShirtViewModel.setPrice(new BigDecimal("25.99"));
        tShirtViewModel.setQuantity(20);

        tShirtViewModel = gameStoreService.saveTShirt(tShirtViewModel);
        List<TShirtViewModel> tShirtList = new ArrayList<>();
        tShirtList.add(tShirtViewModel);
        List<TShirtViewModel> fromService = gameStoreService.findAllTShirts();
        assertEquals(tShirtList, fromService);
    }

    @Test
    public void shouldFindTShirtsBySize() {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.setSize("X-Large");
        tShirtViewModel.setColor("Red");
        tShirtViewModel.setDescription("Red shirt");
        tShirtViewModel.setPrice(new BigDecimal("25.99"));
        tShirtViewModel.setQuantity(20);

        tShirtViewModel = gameStoreService.saveTShirt(tShirtViewModel);
        List<TShirtViewModel> tShirtList = new ArrayList<>();
        tShirtList.add(tShirtViewModel);
        List<TShirtViewModel> fromService = gameStoreService.findTShirtsBySize(tShirtViewModel.getSize());
        assertEquals(tShirtList, fromService);
    }

    @Test
    public void shouldFindTShirtsByColor() {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.setSize("X-Large");
        tShirtViewModel.setColor("Red");
        tShirtViewModel.setDescription("Red shirt");
        tShirtViewModel.setPrice(new BigDecimal("25.99"));
        tShirtViewModel.setQuantity(20);

        tShirtViewModel = gameStoreService.saveTShirt(tShirtViewModel);
        List<TShirtViewModel> tShirtList = new ArrayList<>();
        tShirtList.add(tShirtViewModel);
        List<TShirtViewModel> fromService = gameStoreService.findTShirtsByColor(tShirtViewModel.getColor());
        assertEquals(tShirtList, fromService);
    }

    // **********
    // INVOICE API
    // **********

    @Test
    public void shouldSaveAndFindInvoice() {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Doe");
        invoiceViewModel.setStreet("Main Street");
        invoiceViewModel.setCity("Jersey City");
        invoiceViewModel.setState("NJ");
        invoiceViewModel.setZipcode("55555");
        invoiceViewModel.setItemType("Console");
        invoiceViewModel.setItemId(1);
        invoiceViewModel.setQuantity(1);

        invoiceViewModel = gameStoreService.saveInvoice(invoiceViewModel);
        InvoiceViewModel fromService = gameStoreService.findInvoiceById(invoiceViewModel.getId());
        assertEquals(invoiceViewModel, fromService);
    }

    @Test
    public void shouldFindAllInvoices() {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("John Doe");
        invoiceViewModel.setStreet("Main Street");
        invoiceViewModel.setCity("Jersey City");
        invoiceViewModel.setState("NJ");
        invoiceViewModel.setZipcode("55555");
        invoiceViewModel.setItemType("Console");
        invoiceViewModel.setItemId(1);
        invoiceViewModel.setQuantity(1);

        invoiceViewModel = gameStoreService.saveInvoice(invoiceViewModel);
        List<InvoiceViewModel> invoiceList = new ArrayList<>();
        invoiceList.add(invoiceViewModel);
        List<InvoiceViewModel> fromService = gameStoreService.findAllInvoices();
        assertEquals(invoiceList, fromService);
    }
    
    // **********
    // SET UP METHODS
    // **********

    private void setUpGameDaoMock() {
        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setId(1);
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        Game game1 = new Game();
        game1.setTitle("Madden 2019");
        game1.setEsrbRating("G");
        game1.setDescription("Newest Madden NFL game.");
        game1.setStudio("EA Sports");
        game1.setPrice(new BigDecimal("69.99"));
        game1.setQuantity(25);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameDao).addGame(game1);
        doReturn(game).when(gameDao).getGameById(1);
        doReturn(gameList).when(gameDao).getAllGames();
        doReturn(gameList).when(gameDao).getGamesByStudio("EA Sports");
        doReturn(gameList).when(gameDao).getGamesByRating("G");
        doReturn(gameList).when(gameDao).getGameByTitle("Madden 2019");
    }

    private void setUpConsoleDaoMock() {
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();
        console.setId(1);
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1TB");
        console.setProcessor("APU");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        Console console1 = new Console();
        console1.setModel("PlayStation 4");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("1TB");
        console1.setProcessor("APU");
        console1.setPrice(new BigDecimal("299.99"));
        console1.setQuantity(25);
        
        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);
        
        doReturn(console).when(consoleDao).addConsole(console1);
        doReturn(console).when(consoleDao).getConsoleById(1);
        doReturn(consoleList).when(consoleDao).getAllConsoles();
        doReturn(consoleList).when(consoleDao).getConsolesByManufacturer("Sony");
    }

    private void setUpTShirtDaoMock() {
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirt tShirt = new TShirt();
        tShirt.setId(1);
        tShirt.setSize("X-Large");
        tShirt.setColor("Red");
        tShirt.setDescription("Red shirt");
        tShirt.setPrice(new BigDecimal("25.99"));
        tShirt.setQuantity(20);

        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("X-Large");
        tShirt1.setColor("Red");
        tShirt1.setDescription("Red shirt");
        tShirt1.setPrice(new BigDecimal("25.99"));
        tShirt1.setQuantity(20);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt);

        doReturn(tShirt).when(tShirtDao).addTShirt(tShirt1);
        doReturn(tShirt).when(tShirtDao).getTShirtById(1);
        doReturn(tShirtList).when(tShirtDao).getAllTShirts();
        doReturn(tShirtList).when(tShirtDao).getTShirtsBySize("X-Large");
        doReturn(tShirtList).when(tShirtDao).getTShirtsByColor("Red");
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setName("John Doe");
        invoice.setStreet("Main Street");
        invoice.setCity("Jersey City");
        invoice.setState("NJ");
        invoice.setZipcode("55555");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal("299.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("299.99"));
        invoice.setTax(new BigDecimal("18.00"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("332.98"));

        Invoice invoice1 = new Invoice();
        invoice1.setName("John Doe");
        invoice1.setStreet("Main Street");
        invoice1.setCity("Jersey City");
        invoice1.setState("NJ");
        invoice1.setZipcode("55555");
        invoice1.setItemType("Console");
        invoice1.setItemId(1);
        invoice1.setUnitPrice(new BigDecimal("299.99"));
        invoice1.setQuantity(1);
        invoice1.setSubtotal(new BigDecimal("299.99"));
        invoice1.setTax(new BigDecimal("18.00"));
        invoice1.setProcessingFee(new BigDecimal("14.99"));
        invoice1.setTotal(new BigDecimal("332.98"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoice).when(invoiceDao).getInvoiceById(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
    }

    private void setUpSalesTaxRateDaoMock() {
        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(new BigDecimal(".06"));

        doReturn(salesTaxRate).when(salesTaxRateDao).addSalesTaxRate(salesTaxRate);
        doReturn(salesTaxRate).when(salesTaxRateDao).getSalesTaxRateByState("NJ");
    }

    private void setUpProcessingFeeDaoMock() {
        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Console");
        processingFee.setFee(new BigDecimal("14.99"));

        doReturn(processingFee).when(processingFeeDao).addProcessingFee(processingFee);
        doReturn(processingFee).when(processingFeeDao).getProcessingFeeByType("Console");
    }
}