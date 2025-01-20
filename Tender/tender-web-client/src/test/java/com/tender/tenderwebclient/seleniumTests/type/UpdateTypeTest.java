package com.tender.tenderwebclient.seleniumTests.type;

import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateTypeTest {
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
    public void testUpdateType() {
        String updatedName = "Updated Type Name";
        String updatedSlug = "updated-type-slug";

        AllTendersPage allTendersPage = new AllTendersPage(driver);
        allTendersPage.open();

        TenderDetailsPage tenderDetailsPage = allTendersPage.clickFirstTenderDetails();
        UpdateTypePage updateTypePage = tenderDetailsPage.clickUpdateType();

        updateTypePage.updateName(updatedName)
                .updateSlug(updatedSlug)
                .submitForm();

        String actualName = tenderDetailsPage.getTypeName();
        String actualSlug = tenderDetailsPage.getTypeSlug();
        assertEquals(updatedName, actualName);
        assertEquals(updatedSlug, actualSlug);
    }

}

