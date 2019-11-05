package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.Console;
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
public class ConsoleDaoTest {
    @Autowired
    ConsoleDao consoleDao;

    private Console console;
    private Console console2;

    @Before
    public void setUp() throws Exception {
        clearDatabase();
        setUpTestObjects();
    }

    public void clearDatabase() {
        consoleDao.getAllConsoles().forEach(console -> consoleDao.deleteConsole(console.getId()));
    }

    public void setUpTestObjects() {
        console = new Console();
        console.setModel("PlayStation4");
        console.setManufacturer("PlayStation");
        console.setMemoryAmount("1TB");
        console.setProcessor("i7");
        console.setPrice(new BigDecimal("399.99"));
        console.setQuantity(50);

        console2 = new Console();
        console2.setModel("Xbox One");
        console2.setManufacturer("Xbox");
        console2.setMemoryAmount("1TB");
        console2.setProcessor("Xbox");
        console2.setPrice(new BigDecimal("299.99"));
        console2.setQuantity(75);
    }

    @Test
    public void shouldAddGetDeleteConsole() {
        console = consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsoleById(console.getId());
        assertEquals(console, console1);

        consoleDao.deleteConsole(console.getId());
        console1 = consoleDao.getConsoleById(console.getId());
        assertNull(console1);
    }

    @Test
    public void shouldGetAllConsoles() {
        consoleDao.addConsole(console);
        consoleDao.addConsole(console2);

        List<Console> consoles = consoleDao.getAllConsoles();
        assertEquals(2, consoles.size());
    }

    @Test
    public void shouldUpdateConsole() {
        console = consoleDao.addConsole(console);
        console.setModel("UPDATED MODEL");
        console.setManufacturer("UPDATED MANUFACTURER");
        console.setMemoryAmount("UPDATED MEMORY");
        console.setProcessor("UPDATED PROCESSOR");
        console.setPrice(new BigDecimal("99.99"));
        console.setQuantity(1);
        consoleDao.updateConsole(console);

        Console console1 = consoleDao.getConsoleById(console.getId());
        assertEquals(console, console1);
    }

    @Test
    public void shouldGetConsolesByManufacturer() {
        consoleDao.addConsole(console);
        consoleDao.addConsole(console2);

        List<Console> consoles = consoleDao.getConsolesByManufacturer("PlayStation");
        assertEquals(1, consoles.size());

        List<Console> consoles2 = consoleDao.getConsolesByManufacturer("Xbox");
        assertEquals(1, consoles2.size());
    }
}