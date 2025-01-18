package com.tender.tenderwebclient.seleniumTests.tender.tests;

import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import com.tender.tenderwebclient.seleniumTests.tender.TenderDetailsPage;
import com.tender.tenderwebclient.seleniumTests.tender.UpdateTenderPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateTenderTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void testUpdateTender() {
        // Arrange
        String newTitle = "Updated Title";
        String newCategory = "Updated Category";

        // Act
        AllTendersPage allTendersPage = new AllTendersPage(driver);
        allTendersPage.open();

        TenderDetailsPage tenderDetailsPage = allTendersPage.clickFirstTenderDetails();
        UpdateTenderPage updateTenderPage = tenderDetailsPage.clickUpdateTender();

        updateTenderPage.updateTitle(newTitle)
                .updateCategory(newCategory)
                .submitForm();

        // Assert
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("TenderDetails"));
    }
}
