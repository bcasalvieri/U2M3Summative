package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.ProcessingFee;
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
public class ProcessingFeeDaoTest {
    @Autowired
    ProcessingFeeDao processingFeeDao;

    private ProcessingFee processingFee;

    @Before
    public void setUp() throws Exception {
        clearDatabase();
        setUpTestObjects();
    }

    public void clearDatabase() {
        processingFeeDao.getAllProcessingFees().forEach(processingFee1 -> processingFeeDao.deleteProcessingFee(processingFee1.getProductType()));
    }

    public void setUpTestObjects() {
        processingFee = new ProcessingFee();
        processingFee.setProductType("console");
        processingFee.setFee(new BigDecimal("14.99"));
    }

    @Test
    public void addProcessingFee() {
        processingFeeDao.addProcessingFee(processingFee);

        ProcessingFee processingFee1 = processingFeeDao.getProcessingFeeByType(processingFee.getProductType());
        assertEquals(processingFee, processingFee1);

        processingFeeDao.deleteProcessingFee(processingFee.getProductType());
        processingFee1 = processingFeeDao.getProcessingFeeByType(processingFee.getProductType());
        assertNull(processingFee1);
    }

    @Test
    public void getAllProcessingFees() {
        processingFeeDao.addProcessingFee(processingFee);

        List<ProcessingFee> processingFees = processingFeeDao.getAllProcessingFees();
        assertEquals(1, processingFees.size());
    }

    @Test
    public void updateProcessingFee() {
        processingFeeDao.addProcessingFee(processingFee);
        processingFee.setFee(new BigDecimal("19.99"));
        processingFeeDao.updateProcessingFee(processingFee);

        ProcessingFee processingFee1 = processingFeeDao.getProcessingFeeByType(processingFee.getProductType());
        assertEquals(processingFee, processingFee1);
   }
}