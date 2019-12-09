package com.company.BradCasalvieriU2M3Summative.dao;

import com.company.BradCasalvieriU2M3Summative.dto.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTaxRateRepository extends JpaRepository<SalesTaxRate, Integer> {
    SalesTaxRate findByState(String state);
}
