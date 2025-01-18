package com.tender.tenderwebclient.seleniumTests.supplier.tests;

import com.tender.tenderwebclient.seleniumTests.supplier.AllSuppliersPage;
import com.tender.tenderwebclient.seleniumTests.supplier.RemoveSupplierPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoveSupplierTest {
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
    public void testRemoveSupplier() {
        // Act
        AllSuppliersPage allSuppliersPage = new AllSuppliersPage(driver);
        allSuppliersPage.open();

        RemoveSupplierPage removeSupplierPage = allSuppliersPage.clickLastRemoveButton();
        removeSupplierPage.confirmDeletion();

        // Assert
        assertTrue(driver.getCurrentUrl().contains("allSuppliers"));
    }
}
