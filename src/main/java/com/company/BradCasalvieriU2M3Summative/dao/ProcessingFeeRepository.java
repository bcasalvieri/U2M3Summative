package com.company.BradCasalvieriU2M3Summative.dao;

import com.company.BradCasalvieriU2M3Summative.dto.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepository extends JpaRepository<ProcessingFee, Integer> {
    ProcessingFee findByProductType(String productType);
}
