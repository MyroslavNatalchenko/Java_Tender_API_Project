package com.tender.tenderwebclient.seleniumTests.supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddSupplierPage {
    WebDriver driver;

    @FindBy(id = "sourceId")
    private WebElement sourceIdInput;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "slug")
    private WebElement slugInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public AddSupplierPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddSupplierPage open() {
        this.driver.get("http://localhost:8888/addSupplier");
        return this;
    }

    public AddSupplierPage fillSourceId(String sourceId) {
        this.sourceIdInput.clear();
        this.sourceIdInput.sendKeys(sourceId);
        return this;
    }

    public AddSupplierPage fillName(String name) {
        this.nameInput.clear();
        this.nameInput.sendKeys(name);
        return this;
    }

    public AddSupplierPage fillSlug(String slug) {
        this.slugInput.clear();
        this.slugInput.sendKeys(slug);
        return this;
    }

    public ViewAllSuppliers submitForm() {
        this.submitButton.click();
        return new ViewAllSuppliers(driver);
    }
}
