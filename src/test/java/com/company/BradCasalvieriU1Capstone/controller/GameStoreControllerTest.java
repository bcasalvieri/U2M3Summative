package com.company.BradCasalvieriU1Capstone.controller;

import com.company.BradCasalvieriU1Capstone.dao.*;
import com.company.BradCasalvieriU1Capstone.model.*;
import com.company.BradCasalvieriU1Capstone.service.GameStoreService;
import com.company.BradCasalvieriU1Capstone.viewmodel.ConsoleViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.GameViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.InvoiceViewModel;
import com.company.BradCasalvieriU1Capstone.viewmodel.TShirtViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@WebMvcTest(GameStoreController.class)
public class GameStoreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameStoreService gameStoreService;

    private ObjectMapper mapper = new ObjectMapper();

    private List<GameViewModel> gameViewModelList;
    private List<ConsoleViewModel> consoleViewModelList;
    private List<TShirtViewModel> tShirtViewModelList;
    private List<InvoiceViewModel> invoiceViewModelList;

    @Before
    public void setUp() throws Exception {
        setUpGameServiceMock();
        setUpGameTestObjects();
        setUpConsoleServiceMock();
        setUpConsoleTestObjects();
        setUpTShirtServiceMock();
        setUpTShirtTestObjects();
        setUpInvoiceServiceMock();
        setUpInvoiceTestObjects();
    }

    // **********
    // GAME TESTS
    // **********

    @Test
    public void shouldPostGameAndReturn201StatusCode() throws Exception {
        GameViewModel inputGame = new GameViewModel();
        inputGame.setTitle("Madden 2019");
        inputGame.setEsrbRating("G");
        inputGame.setDescription("Newest Madden NFL game.");
        inputGame.setStudio("EA Sports");
        inputGame.setPrice(new BigDecimal("69.99"));
        inputGame.setQuantity(25);

        String inputJson = mapper.writeValueAsString(inputGame);

        GameViewModel outputGame = new GameViewModel();
        outputGame.setId(1);
        outputGame.setTitle("Madden 2019");
        outputGame.setEsrbRating("G");
        outputGame.setDescription("Newest Madden NFL game.");
        outputGame.setStudio("EA Sports");
        outputGame.setPrice(new BigDecimal("69.99"));
        outputGame.setQuantity(25);

        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(
                post("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422ErrorWhenPostingGame() throws Exception {
        GameViewModel inputGame = new GameViewModel();
        inputGame.setTitle("Madden 2019");
        inputGame.setEsrbRating("G");
        inputGame.setDescription("Newest Madden NFL game.");
        inputGame.setStudio("EA Sports");
        inputGame.setPrice(new BigDecimal("69.99"));

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                post("/games")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllGamesAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(gameViewModelList);

        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByTitleAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(gameViewModelList);

        mockMvc.perform(get("/games/title/Madden 2019"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByStudioAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(gameViewModelList);

        mockMvc.perform(get("/games/studio/EA Sports"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGamesByRatingAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(gameViewModelList);

        mockMvc.perform(get("/games/rating/G"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetGameByIdAndReturn200StatusCode() throws Exception {
        GameViewModel outputGame = gameViewModelList.get(0);
        String outputJson = mapper.writeValueAsString(outputGame);

        mockMvc.perform(get("/game/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn404StatusCodeWhenNoGameMatchingId() throws Exception {
        mockMvc.perform(get("/game/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateGameAndReturn204StatusCode() throws Exception {
        GameViewModel inputGame = new GameViewModel();
        inputGame.setTitle("Call Of Duty");
        inputGame.setEsrbRating("MA");
        inputGame.setDescription("Newest Call Of Duty game.");
        inputGame.setStudio("Activision");
        inputGame.setPrice(new BigDecimal("59.99"));
        inputGame.setQuantity(25);
        inputGame.setId(1);

        String inputJson = mapper.writeValueAsString(inputGame);

        mockMvc.perform(
                put("/game/1")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteGameAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/game/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    
    // **********
    // CONSOLE TESTS
    // **********

    @Test
    public void shouldPostConsoleAndReturn201StatusCode() throws Exception {
        ConsoleViewModel inputConsole = new ConsoleViewModel();
        inputConsole.setModel("PlayStation 4");
        inputConsole.setManufacturer("Sony");
        inputConsole.setMemoryAmount("1TB");
        inputConsole.setProcessor("APU");
        inputConsole.setPrice(new BigDecimal("299.99"));
        inputConsole.setQuantity(25);

        String inputJson = mapper.writeValueAsString(inputConsole);

        ConsoleViewModel outputConsole = new ConsoleViewModel();
        outputConsole.setId(1);
        outputConsole.setModel("PlayStation 4");
        outputConsole.setManufacturer("Sony");
        outputConsole.setMemoryAmount("1TB");
        outputConsole.setProcessor("APU");
        outputConsole.setPrice(new BigDecimal("299.99"));
        outputConsole.setQuantity(25);

        String outputJson = mapper.writeValueAsString(outputConsole);

        mockMvc.perform(
                post("/consoles")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422ErrorWhenPostingConsole() throws Exception {
        ConsoleViewModel inputConsole = new ConsoleViewModel();
        inputConsole.setId(1);
        inputConsole.setModel("PlayStation 4");
        inputConsole.setManufacturer("Sony");
        inputConsole.setMemoryAmount("1TB");
        inputConsole.setProcessor("APU");
        inputConsole.setPrice(new BigDecimal("299.99"));

        String inputJson = mapper.writeValueAsString(inputConsole);

        mockMvc.perform(
                post("/consoles")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllConsolesAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(consoleViewModelList);

        mockMvc.perform(get("/consoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetConsolesByManufacturerAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(consoleViewModelList);

        mockMvc.perform(get("/consoles/manufacturer/Sony"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetConsoleByIdAndReturn200StatusCode() throws Exception {
        ConsoleViewModel outputConsole = consoleViewModelList.get(0);
        String outputJson = mapper.writeValueAsString(outputConsole);

        mockMvc.perform(get("/console/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn404StatusCodeWhenNoConsoleMatchingId() throws Exception {
        mockMvc.perform(get("/console/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateConsoleAndReturn204StatusCode() throws Exception {
        ConsoleViewModel inputConsole = new ConsoleViewModel();
        inputConsole.setId(1);
        inputConsole.setModel("XBox 1");
        inputConsole.setManufacturer("XBox");
        inputConsole.setMemoryAmount("500MB");
        inputConsole.setProcessor("APU2");
        inputConsole.setPrice(new BigDecimal("399.99"));
        inputConsole.setQuantity(25);

        String inputJson = mapper.writeValueAsString(inputConsole);

        mockMvc.perform(
                put("/console/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteConsoleAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/console/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    
    // **********
    // TSHIRT TESTS
    // **********

    @Test
    public void shouldPostTShirtAndReturn201StatusCode() throws Exception {
        TShirtViewModel inputTShirt = new TShirtViewModel();
        inputTShirt.setSize("X-Large");
        inputTShirt.setColor("Red");
        inputTShirt.setDescription("Red shirt");
        inputTShirt.setPrice(new BigDecimal("25.99"));
        inputTShirt.setQuantity(20);

        String inputJson = mapper.writeValueAsString(inputTShirt);

        TShirtViewModel outputTShirt = new TShirtViewModel();
        outputTShirt.setId(1);
        outputTShirt.setSize("X-Large");
        outputTShirt.setColor("Red");
        outputTShirt.setDescription("Red shirt");
        outputTShirt.setPrice(new BigDecimal("25.99"));
        outputTShirt.setQuantity(20);

        String outputJson = mapper.writeValueAsString(outputTShirt);

        mockMvc.perform(
                post("/tshirts")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422ErrorWhenPostingTShirt() throws Exception {
        TShirtViewModel inputTShirt = new TShirtViewModel();
        inputTShirt.setSize("X-Large");
        inputTShirt.setColor("Red");
        inputTShirt.setDescription("Red shirt");
        inputTShirt.setPrice(new BigDecimal("25.99"));

        String inputJson = mapper.writeValueAsString(inputTShirt);

        mockMvc.perform(
                post("/tshirts")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllTShirtsAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(tShirtViewModelList);

        mockMvc.perform(get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTShirtsBySizeAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(tShirtViewModelList);

        mockMvc.perform(get("/tshirts/size/X-Large"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTShirtsByColorAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(tShirtViewModelList);

        mockMvc.perform(get("/tshirts/color/Red"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldGetTShirtByIdAndReturn200StatusCode() throws Exception {
        TShirtViewModel outputTShirt = tShirtViewModelList.get(0);
        String outputJson = mapper.writeValueAsString(outputTShirt);

        mockMvc.perform(get("/tshirt/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn404StatusCodeWhenNoTShirtMatchingId() throws Exception {
        mockMvc.perform(get("/tshirt/2"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateTShirtAndReturn204StatusCode() throws Exception {
        TShirtViewModel inputTShirt = new TShirtViewModel();
        inputTShirt.setSize("Medium");
        inputTShirt.setColor("Blue");
        inputTShirt.setDescription("Blue shirt");
        inputTShirt.setPrice(new BigDecimal("15.99"));
        inputTShirt.setQuantity(10);

        String inputJson = mapper.writeValueAsString(inputTShirt);

        mockMvc.perform(
                put("/tshirt/1")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTShirtAndReturn204StatusCode() throws Exception {
        mockMvc.perform(delete("/tshirt/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
    
    // **********
    // INVOICE TESTS
    // **********

    @Test
    public void shouldPostInvoiceAndReturn201StatusCode() throws Exception {
        InvoiceViewModel inputInvoice = new InvoiceViewModel();
        inputInvoice.setName("John Doe");
        inputInvoice.setStreet("Main Street");
        inputInvoice.setCity("Jersey City");
        inputInvoice.setState("NJ");
        inputInvoice.setZipcode("55555");
        inputInvoice.setItemType("Console");
        inputInvoice.setItemId(1);
        inputInvoice.setQuantity(1);
        
        String inputJson = mapper.writeValueAsString(inputInvoice);

        InvoiceViewModel outputInvoice = invoiceViewModelList.get(0);

        String outputJson = mapper.writeValueAsString(outputInvoice);

        mockMvc.perform(
                post("/invoices")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void shouldReturn422ErrorWhenPostingInvoice() throws Exception {
        InvoiceViewModel inputInvoice = new InvoiceViewModel();
        inputInvoice.setName("John Doe");
        inputInvoice.setStreet("Main Street");
        inputInvoice.setCity("Jersey City");
        inputInvoice.setState("NJ");
        inputInvoice.setZipcode("55555");
        inputInvoice.setItemType("Console");
        inputInvoice.setItemId(1);

        String inputJson = mapper.writeValueAsString(inputInvoice);

        mockMvc.perform(
                post("/invoices")
                    .content(inputJson)
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldGetAllInvoicesAndReturn200StatusCode() throws Exception {
        String outputJson = mapper.writeValueAsString(invoiceViewModelList);

        mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    // **********
    // HELPER METHODS
    // **********

    private void setUpGameTestObjects() {
        GameViewModel game = new GameViewModel();
        game.setId(1);
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        gameViewModelList = new ArrayList<>();
        gameViewModelList.add(game);
    }
    
    private void setUpConsoleTestObjects() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setId(1);
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1TB");
        console.setProcessor("APU");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        consoleViewModelList = new ArrayList<>();
        consoleViewModelList.add(console);
    }
    
    private void setUpTShirtTestObjects() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setId(1);
        tShirt.setSize("X-Large");
        tShirt.setColor("Red");
        tShirt.setDescription("Red shirt");
        tShirt.setPrice(new BigDecimal("25.99"));
        tShirt.setQuantity(20);
        
        tShirtViewModelList = new ArrayList<>();
        tShirtViewModelList.add(tShirt);
    }
    
    private void setUpInvoiceTestObjects() {
        InvoiceViewModel invoice = new InvoiceViewModel();
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
        
        invoiceViewModelList = new ArrayList<>();
        invoiceViewModelList.add(invoice);
    }

    private void setUpGameServiceMock() {
        GameViewModel game = new GameViewModel();
        game.setId(1);
        game.setTitle("Madden 2019");
        game.setEsrbRating("G");
        game.setDescription("Newest Madden NFL game.");
        game.setStudio("EA Sports");
        game.setPrice(new BigDecimal("69.99"));
        game.setQuantity(25);

        GameViewModel game1 = new GameViewModel();
        game1.setTitle("Madden 2019");
        game1.setEsrbRating("G");
        game1.setDescription("Newest Madden NFL game.");
        game1.setStudio("EA Sports");
        game1.setPrice(new BigDecimal("69.99"));
        game1.setQuantity(25);

        List<GameViewModel> gameList = new ArrayList<>();
        gameList.add(game);

        doReturn(game).when(gameStoreService).saveGame(game1);
        doReturn(game).when(gameStoreService).findGameById(1);
        doReturn(gameList).when(gameStoreService).findAllGames();
        doReturn(gameList).when(gameStoreService).findGamesByStudio("EA Sports");
        doReturn(gameList).when(gameStoreService).findGamesByRating("G");
        doReturn(gameList).when(gameStoreService).findGamesByTitle("Madden 2019");
    }

    private void setUpConsoleServiceMock() {
        ConsoleViewModel console = new ConsoleViewModel();
        console.setId(1);
        console.setModel("PlayStation 4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1TB");
        console.setProcessor("APU");
        console.setPrice(new BigDecimal("299.99"));
        console.setQuantity(25);

        ConsoleViewModel console1 = new ConsoleViewModel();
        console1.setModel("PlayStation 4");
        console1.setManufacturer("Sony");
        console1.setMemoryAmount("1TB");
        console1.setProcessor("APU");
        console1.setPrice(new BigDecimal("299.99"));
        console1.setQuantity(25);

        List<ConsoleViewModel> consoleList = new ArrayList<>();
        consoleList.add(console);

        doReturn(console).when(gameStoreService).saveConsole(console1);
        doReturn(console).when(gameStoreService).findConsoleById(1);
        doReturn(consoleList).when(gameStoreService).findAllConsoles();
        doReturn(consoleList).when(gameStoreService).findConsolesByManufacturer("Sony");
    }

    private void setUpTShirtServiceMock() {
        TShirtViewModel tShirt = new TShirtViewModel();
        tShirt.setId(1);
        tShirt.setSize("X-Large");
        tShirt.setColor("Red");
        tShirt.setDescription("Red shirt");
        tShirt.setPrice(new BigDecimal("25.99"));
        tShirt.setQuantity(20);

        TShirtViewModel tShirt1 = new TShirtViewModel();
        tShirt1.setSize("X-Large");
        tShirt1.setColor("Red");
        tShirt1.setDescription("Red shirt");
        tShirt1.setPrice(new BigDecimal("25.99"));
        tShirt1.setQuantity(20);

        List<TShirtViewModel> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt);

        doReturn(tShirt).when(gameStoreService).saveTShirt(tShirt1);
        doReturn(tShirt).when(gameStoreService).findTShirtById(1);
        doReturn(tShirtList).when(gameStoreService).findAllTShirts();
        doReturn(tShirtList).when(gameStoreService).findTShirtsBySize("X-Large");
        doReturn(tShirtList).when(gameStoreService).findTShirtsByColor("Red");
    }

    private void setUpInvoiceServiceMock() {
        InvoiceViewModel invoice = new InvoiceViewModel();
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

        InvoiceViewModel invoice1 = new InvoiceViewModel();
        invoice1.setName("John Doe");
        invoice1.setStreet("Main Street");
        invoice1.setCity("Jersey City");
        invoice1.setState("NJ");
        invoice1.setZipcode("55555");
        invoice1.setItemType("Console");
        invoice1.setItemId(1);
        invoice1.setQuantity(1);

        List<InvoiceViewModel> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        doReturn(invoice).when(gameStoreService).saveInvoice(invoice1);
        doReturn(invoice).when(gameStoreService).findInvoiceById(1);
        doReturn(invoiceList).when(gameStoreService).findAllInvoices();
    }

    private void setUpSalesTaxRateServiceMock() {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(new BigDecimal(".06"));

        doReturn(salesTaxRate).when(gameStoreService).findSalesTaxRateByState("NJ");
    }

    private void setUpProcessingFeeDaoMock() {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Console");
        processingFee.setFee(new BigDecimal("14.99"));

        doReturn(processingFee).when(gameStoreService).findProcessingFeeByType("Console");
    }
}