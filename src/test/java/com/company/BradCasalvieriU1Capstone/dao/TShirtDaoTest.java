package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtDaoTest {
    @Autowired
    TShirtDao tShirtDao;

    private TShirt tShirt;
    private TShirt tShirt2;

    @Before
    public void setUp() throws Exception {
        clearDatabase();
        setUpTestObjects();
    }

    public void clearDatabase() {
        tShirtDao.getAllTShirts().forEach(tShirt -> tShirtDao.deleteTShirt(tShirt.getId()));
    }

    public void setUpTestObjects() {
        tShirt = new TShirt();
        tShirt.setSize("X-Large");
        tShirt.setColor("Red");
        tShirt.setDescription("Red shirt");
        tShirt.setPrice(new BigDecimal("9.99"));
        tShirt.setQuantity(45);

        tShirt2 = new TShirt();
        tShirt2.setSize("Medium");
        tShirt2.setColor("Blue");
        tShirt2.setDescription("Blue shirt");
        tShirt2.setPrice(new BigDecimal("15.49"));
        tShirt2.setQuantity(35);
    }


    @Test
    public void shouldAddGetDeleteTShirt() {
        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirtById(tShirt.getId());
        assertEquals(tShirt, tShirt1);

        tShirtDao.deleteTShirt(tShirt.getId());
        tShirt1 = tShirtDao.getTShirtById(tShirt.getId());
        assertNull(tShirt1);
    }

    @Test
    public void shouldGetAllTShirts() {
        tShirtDao.addTShirt(tShirt);
        tShirtDao.addTShirt(tShirt2);

        List<TShirt> tShirts = tShirtDao.getAllTShirts();
        assertEquals(2, tShirts.size());
    }

    @Test
    public void shouldUpdateTShirt() {
        tShirt = tShirtDao.addTShirt(tShirt);
        tShirt.setSize("Large");
        tShirt.setColor("White");
        tShirt.setDescription("White shirt");
        tShirt.setPrice(new BigDecimal("0.99"));
        tShirt.setQuantity(25);
        tShirtDao.updateTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirtById(tShirt.getId());
        assertEquals(tShirt, tShirt1);
    }

    @Test
    public void shouldGetTShirtsByColor() {
        tShirtDao.addTShirt(tShirt);
        tShirtDao.addTShirt(tShirt2);

        List<TShirt> tShirts = tShirtDao.getTShirtsByColor("Red");
        assertEquals(1, tShirts.size());

        List<TShirt> tShirts2 = tShirtDao.getTShirtsByColor("Blue");
        assertEquals(1, tShirts2.size());
    }

    @Test
    public void shouldGetTShirtsBySize() {
        tShirtDao.addTShirt(tShirt);
        tShirtDao.addTShirt(tShirt2);

        List<TShirt> tShirts = tShirtDao.getTShirtsBySize("X-Large");
        assertEquals(1, tShirts.size());

        List<TShirt> tShirts2 = tShirtDao.getTShirtsBySize("Medium");
        assertEquals(1, tShirts2.size());
    }
}