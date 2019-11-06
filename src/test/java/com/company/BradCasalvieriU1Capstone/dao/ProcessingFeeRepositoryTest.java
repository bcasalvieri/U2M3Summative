package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.ProcessingFee;
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
public class ProcessingFeeRepositoryTest {
    @Autowired
    ProcessingFeeRepository processingFeeRepo;

    @Before
    public void setUp() throws Exception {
        processingFeeRepo.deleteAll();
    }

    @Test
    public void findByProductType() {
        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProductType("Console");
        processingFee.setFee(new BigDecimal("14.98"));
        processingFee = processingFeeRepo.save(processingFee);

        ProcessingFee processingFee1 = processingFeeRepo.findByProductType("Console");
        assertEquals(processingFee, processingFee1);
    }
}