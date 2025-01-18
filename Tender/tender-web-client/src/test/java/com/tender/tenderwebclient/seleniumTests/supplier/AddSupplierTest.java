package com.tender.tenderwebclient.seleniumTests.supplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddSupplierTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void testAddSupplierForm() {
        // Arrange
        String expectedSourceId = "9999";
        String expectedName = "Test Supplier";
        String expectedSlug = "test-supplier";

        // Act
        AddSupplierPage addSupplier = new AddSupplierPage(driver);
        addSupplier.open()
                .fillSourceId(expectedSourceId)
                .fillName(expectedName)
                .fillSlug(expectedSlug);
        ViewAllSuppliers allSuppliers = addSupplier.submitForm();

        assertEquals("All Suppliers", allSuppliers.getHeader());

        WebElement table = driver.findElement(By.id("suppliersTable"));
        List<WebElement> rows = table.findElements(By.cssSelector(".supplier-row"));
        WebElement lastRow = rows.get(rows.size() - 1);

        List<WebElement> cells = lastRow.findElements(By.tagName("td"));
        assertEquals(expectedSourceId, cells.get(1).getText());
        assertEquals(expectedName, cells.get(2).getText());
        assertEquals(expectedSlug, cells.get(3).getText());
    }
}
