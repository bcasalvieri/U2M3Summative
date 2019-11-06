package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.SalesTaxRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SalesTaxRateRepositoryTest {
    @Autowired
    SalesTaxRateRepository salesTaxRateRepo;

    @Test
    public void findByState() {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(new BigDecimal(".06"));
        salesTaxRate = salesTaxRateRepo.save(salesTaxRate);

        SalesTaxRate salesTaxRate1 = salesTaxRateRepo.findByState("NJ");
        assertEquals(salesTaxRate, salesTaxRate1);
    }
}