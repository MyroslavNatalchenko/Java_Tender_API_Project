package com.tender.tenderwebclient.seleniumTests.supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateSupplierPage {
    WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "slug")
    private WebElement slugInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public UpdateSupplierPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UpdateSupplierPage updateName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    public UpdateSupplierPage updateSlug(String slug) {
        slugInput.clear();
        slugInput.sendKeys(slug);
        return this;
    }

    public AllSuppliersPage submitForm() {
        submitButton.click();
        return new AllSuppliersPage(driver);
    }
}
