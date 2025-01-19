package com.tender.tenderwebclient.seleniumTests.supplier;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AllSuppliersPage {
    WebDriver driver;

    @FindBy(css = "table#suppliersTable tbody tr")
    private List<WebElement> supplierRows;

    @FindBy(css = "table#suppliersTable tbody tr:last-child a[href*='/updateSupplier']")
    private WebElement lastUpdateButton;

    @FindBy(css = "table#suppliersTable tbody tr:last-child a[href*='/removeSupplier']")
    private WebElement lastRemoveButton;

    @FindBy(tagName = "h1")
    private WebElement header;

    public AllSuppliersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AllSuppliersPage open() {
        driver.get("http://localhost:8888/allSuppliers");
        return this;
    }

    public List<String> getAllSupplierIds() {
        return supplierRows.stream()
                .map(row -> row.findElement(By.cssSelector("td:nth-child(1)")).getText())
                .collect(Collectors.toList());
    }

    public Map<String, String> getLastSupplierData() {
        WebElement lastRow = supplierRows.get(supplierRows.size() - 1);
        Map<String, String> data = new HashMap<>();
        data.put("id", lastRow.findElement(By.cssSelector("td:nth-child(1)")).getText());
        data.put("name", lastRow.findElement(By.cssSelector("td:nth-child(3)")).getText());
        data.put("slug", lastRow.findElement(By.cssSelector("td:nth-child(4)")).getText());
        return data;
    }

    public UpdateSupplierPage clickLastUpdateButton() {
        lastUpdateButton.click();
        return new UpdateSupplierPage(driver);
    }

    public RemoveSupplierPage clickLastRemoveButton() {
        lastRemoveButton.click();
        return new RemoveSupplierPage(driver);
    }

    public String getHeader() {
        return this.header.getText();
    }
}
