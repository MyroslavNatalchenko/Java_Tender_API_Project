package com.tender.tenderwebclient.seleniumTests.tender.tests;

import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import com.tender.tenderwebclient.seleniumTests.tender.DeleteTenderPage;
import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TenderDeleteTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }

    @Test
    public void testDeleteTender() {
        AllTendersPage allTendersPage = new AllTendersPage(driver);
        allTendersPage.open();

        String firstTenderSourceId = allTendersPage.getFirstTenderSourceId();

        TenderDetailsPage tenderDetailsPage = allTendersPage.clickFirstTenderDetails();
        DeleteTenderPage deleteTenderPage = tenderDetailsPage.clickDeleteTender();
        deleteTenderPage.confirmDelete();

        allTendersPage.open();
        assertFalse(allTendersPage.isTenderPresent(firstTenderSourceId));
    }

}
