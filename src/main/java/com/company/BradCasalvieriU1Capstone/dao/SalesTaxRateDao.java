package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.SalesTaxRate;

import java.util.List;

public interface SalesTaxRateDao {
    SalesTaxRate addSalesTaxRate(SalesTaxRate salesTaxRate);
    SalesTaxRate getSalesTaxRateByState(String state);
    List<SalesTaxRate> getAllSalesTaxRates();
    void updateSalesTaxRate(SalesTaxRate salesTaxRate);
    void deleteSalesTaxRate(String state);
}
