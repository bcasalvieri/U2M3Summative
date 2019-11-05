package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.ProcessingFee;

import java.util.List;

public interface ProcessingFeeDao {
    ProcessingFee addProcessingFee(ProcessingFee processingFee);
    ProcessingFee getProcessingFeeByType(String type);
    List<ProcessingFee> getAllProcessingFees();
    void updateProcessingFee(ProcessingFee processingFee);
    void deleteProcessingFee(String type);
}
