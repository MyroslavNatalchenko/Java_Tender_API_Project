package com.tender.tenderwebclient.seleniumTests.purchaser;

import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdatePurchaserTest {
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
    public void testUpdatePurchaser() {
        String updatedSID = "Updated SID";
        String updatedName = "Updated Name";

        AllTendersPage allTendersPage = new AllTendersPage(driver);
        allTendersPage.open();

        TenderDetailsPage tenderDetailsPage = allTendersPage.clickFirstTenderDetails();
        UpdatePurchaserPage updatePurchaserPage = tenderDetailsPage.clickUpdatePurchaser();

        updatePurchaserPage.updateSID(updatedSID)
                .updateName(updatedName)
                .submitForm();

        String actualSID = tenderDetailsPage.getPurchaserSID();
        String actualName = tenderDetailsPage.getPurchaserName();
        assertEquals(updatedSID, actualSID);
        assertEquals(updatedName, actualName);
    }

}

