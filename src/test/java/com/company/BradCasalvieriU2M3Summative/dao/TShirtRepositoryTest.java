package com.company.BradCasalvieriU2M3Summative.dao;

import com.company.BradCasalvieriU2M3Summative.dto.TShirt;
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
public class TShirtRepositoryTest {
    @Autowired
    TShirtRepository tShirtRepo;

    private TShirt tShirt;

    @Before
    public void setUp() throws Exception {
        tShirtRepo.deleteAll();
        setUpTestObjects();
    }

    public void setUpTestObjects() {
        tShirt = new TShirt();
        tShirt.setSize("X-Large");
        tShirt.setColor("Red");
        tShirt.setDescription("Red shirt");
        tShirt.setPrice(new BigDecimal("9.99"));
        tShirt.setQuantity(45);
    }

    @Test
    public void findBySize() {
        tShirtRepo.save(tShirt);
        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt);

        List<TShirt> tShirtList1 = tShirtRepo.findBySize("X-Large");
        assertEquals(tShirtList, tShirtList1);
    }

    @Test
    public void findByColor() {
        tShirtRepo.save(tShirt);
        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt);

        List<TShirt> tShirtList1 = tShirtRepo.findByColor("Red");
        assertEquals(tShirtList, tShirtList1);
    }
}