package com.tender.tenderwebclient.seleniumTests.supplier.tests;

import com.tender.tenderwebclient.seleniumTests.supplier.AllSuppliersPage;
import com.tender.tenderwebclient.seleniumTests.supplier.UpdateSupplierPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateSupplierTest {
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
    public void testUpdateSupplier() {
        String updatedName = "Updated Supplier Name";
        String updatedSlug = "updated-slug";

        AllSuppliersPage allSuppliersPage = new AllSuppliersPage(driver);
        allSuppliersPage.open();

        Map<String, String> lastSupplierDataBefore = allSuppliersPage.getLastSupplierData();

        UpdateSupplierPage updateSupplierPage = allSuppliersPage.clickLastUpdateButton();
        updateSupplierPage.updateName(updatedName)
                .updateSlug(updatedSlug)
                .submitForm();

        Map<String, String> lastSupplierDataAfter = allSuppliersPage.getLastSupplierData();

        assertEquals(updatedName, lastSupplierDataAfter.get("name"));
        assertEquals(updatedSlug, lastSupplierDataAfter.get("slug"));
    }
}
