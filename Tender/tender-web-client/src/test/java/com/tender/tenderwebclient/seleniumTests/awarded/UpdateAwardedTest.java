package com.tender.tenderwebclient.seleniumTests.awarded;

import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateAwardedTest {
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
    public void testUpdateAwarded() {
        String updatedValueOne = "1000.5";
        String updatedValueTwo = "2000.0";

        AllTendersPage allTendersPage = new AllTendersPage(driver);
        allTendersPage.open();

        TenderDetailsPage tenderDetailsPage = allTendersPage.clickFirstTenderDetails();
        UpdateAwardedPage updateAwardedPage = tenderDetailsPage.clickUpdateAwarded();

        updateAwardedPage.updateValueForOne(updatedValueOne)
                .updateValueForTwo(updatedValueTwo)
                .submitForm();

        String actualValueOne = tenderDetailsPage.getAwardedValueForOne();
        String actualValueTwo = tenderDetailsPage.getAwardedValueForTwo();
        assertEquals(updatedValueOne, actualValueOne);
        assertEquals(updatedValueTwo, actualValueTwo);
    }

}
