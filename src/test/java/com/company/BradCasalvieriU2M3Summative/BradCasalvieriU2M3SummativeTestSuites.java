package com.company.BradCasalvieriU2M3Summative;

import com.company.BradCasalvieriU2M3Summative.controller.GameStoreControllerTest;
import com.company.BradCasalvieriU2M3Summative.dao.*;
import com.company.BradCasalvieriU2M3Summative.service.GameStoreServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GameRepositoryTest.class,
        ConsoleRepositoryTest.class,
        TShirtRepositoryTest.class,
        SalesTaxRateRepositoryTest.class,
        ProcessingFeeRepositoryTest.class,
        GameStoreServiceTest.class,
        GameStoreControllerTest.class
})
public class BradCasalvieriU2M3SummativeTestSuites {
}
