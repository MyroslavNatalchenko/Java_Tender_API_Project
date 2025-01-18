package com.tender.tenderwebclient.seleniumTests.supplier.tests;

import com.tender.tenderwebclient.seleniumTests.supplier.AllSuppliersPage;
import com.tender.tenderwebclient.seleniumTests.supplier.UpdateSupplierPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateSupplierTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

//    @AfterEach
//    public void tearDown() {
//        if (this.driver != null) {
//            this.driver.quit();
//        }
//    }

    @Test
    public void testUpdateSupplier() {
        // Arrange
        String updatedName = "Updated Supplier Name";
        String updatedSlug = "updated-slug";

        // Act
        AllSuppliersPage allSuppliersPage = new AllSuppliersPage(driver);
        allSuppliersPage.open();

        UpdateSupplierPage updateSupplierPage = allSuppliersPage.clickLastUpdateButton();
        updateSupplierPage.updateName(updatedName)
                .updateSlug(updatedSlug)
                .submitForm();

        // Assert
        assertTrue(driver.getCurrentUrl().contains("allSuppliers"));
    }
}
