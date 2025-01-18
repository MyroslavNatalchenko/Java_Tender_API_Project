package com.tender.tenderwebclient.seleniumTests.tender.tests;

import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import com.tender.tenderwebclient.seleniumTests.tender.DeleteTenderPage;
import com.tender.tenderwebclient.seleniumTests.tender.TenderDetailsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TenderDeleteTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void testDeleteTender() {
        AllTendersPage allTendersPage = new AllTendersPage(driver);
        allTendersPage.open();

        //Pierwsza strona tender details
        TenderDetailsPage tenderDetailsPage = allTendersPage.clickFirstTenderDetails();

        //Usuwanie Tenderu
        DeleteTenderPage deleteTenderPage = tenderDetailsPage.clickDeleteTender();
        deleteTenderPage.confirmDelete();
    }
}
