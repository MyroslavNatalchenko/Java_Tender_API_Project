package com.tender.tenderwebclient.seleniumTests.supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemoveSupplierPage {
    WebDriver driver;

    @FindBy(id = "submit")
    private WebElement confirmDeleteButton;

    public RemoveSupplierPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AllSuppliersPage confirmDeletion() {
        confirmDeleteButton.click();
        return new AllSuppliersPage(driver);
    }
}
