package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.Console;
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
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepo;

    private Console console;

    @Before
    public void setUp() throws Exception {
        setUpTestObjects();
    }

    public void setUpTestObjects() {
        console = new Console();
        console.setModel("PlayStation4");
        console.setManufacturer("Sony");
        console.setMemoryAmount("1TB");
        console.setProcessor("i7");
        console.setPrice(new BigDecimal("399.99"));
        console.setQuantity(50);
    }

    @Test
    public void findByManufacturer() {
        consoleRepo.save(console);
        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console);

        List<Console> consoleList1 = consoleRepo.findByManufacturer("Sony");
        assertEquals(consoleList, consoleList1);
    }
}