package com.tender.tenderwebclient.seleniumTests.tender.tests;

import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import com.tender.tenderwebclient.seleniumTests.tender.UpdateTenderPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateTenderTest {
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
    public void testUpdateTender() {
        String updatedTitle = "Updated Title";
        String updatedCategory = "Updated Category";

        AllTendersPage allTendersPage = new AllTendersPage(driver);
        allTendersPage.open();
        TenderDetailsPage tenderDetailsPage = allTendersPage.clickFirstTenderDetails();

        UpdateTenderPage updateTenderPage = tenderDetailsPage.clickUpdateTender();
        updateTenderPage.updateTitle(updatedTitle)
                .updateCategory(updatedCategory)
                .updateDeadlineDate("24.07.2024")
                .submitForm();

        String actualTitle = tenderDetailsPage.getTenderTitle();
        String actualCategory = tenderDetailsPage.getTenderCategory();
        assertEquals(updatedTitle, actualTitle);
        assertEquals(updatedCategory, actualCategory);
    }

}
