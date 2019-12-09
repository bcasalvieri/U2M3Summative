package com.company.BradCasalvieriU2M3Summative.dao;

import com.company.BradCasalvieriU2M3Summative.dto.SalesTaxRate;
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