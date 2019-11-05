package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.SalesTaxRate;
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
public class SalesTaxRateDaoTest {
    @Autowired
    SalesTaxRateDao salesTaxRateDao;

    private SalesTaxRate salesTaxRate;

    @Before
    public void setUp() throws Exception {
        clearDatabase();
        setUpTestObject();
    }

    public void clearDatabase() {
        salesTaxRateDao.getAllSalesTaxRates().forEach(salesTaxRate1 -> salesTaxRateDao.deleteSalesTaxRate(salesTaxRate1.getState()));
    }

    public void setUpTestObject() {
        salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState("NJ");
        salesTaxRate.setRate(new BigDecimal(".05"));
    }

    @Test
    public void shouldAddGetDeleteSalesTaxRate() {
        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);

        SalesTaxRate salesTaxRate1 = salesTaxRateDao.getSalesTaxRateByState(salesTaxRate.getState());
        assertEquals(salesTaxRate, salesTaxRate1);

        salesTaxRateDao.deleteSalesTaxRate(salesTaxRate.getState());
        salesTaxRate1 = salesTaxRateDao.getSalesTaxRateByState(salesTaxRate.getState());
        assertNull(salesTaxRate1);
    }

    @Test
    public void shouldGetAllSalesTaxRates() {
        salesTaxRateDao.addSalesTaxRate(salesTaxRate);

        List<SalesTaxRate> salesTaxRates = salesTaxRateDao.getAllSalesTaxRates();
        assertEquals(1, salesTaxRates.size());
    }

    @Test
    public void shouldUpdateSalesTaxRate() {
        salesTaxRate = salesTaxRateDao.addSalesTaxRate(salesTaxRate);
        salesTaxRate.setRate(new BigDecimal(".06"));
        salesTaxRateDao.updateSalesTaxRate(salesTaxRate);

        SalesTaxRate salesTaxRate1 = salesTaxRateDao.getSalesTaxRateByState(salesTaxRate.getState());
        assertEquals(salesTaxRate, salesTaxRate1);
    }
}