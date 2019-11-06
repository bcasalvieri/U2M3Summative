package com.company.BradCasalvieriU1Capstone;

import com.company.BradCasalvieriU1Capstone.controller.GameStoreControllerTest;
import com.company.BradCasalvieriU1Capstone.dao.*;
import com.company.BradCasalvieriU1Capstone.service.GameStoreServiceTest;
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
public class BradCasalvieriU1CapstoneTestSuites {
}
