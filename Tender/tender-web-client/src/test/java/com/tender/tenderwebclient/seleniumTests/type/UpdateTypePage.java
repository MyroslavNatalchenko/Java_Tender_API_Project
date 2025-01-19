package com.tender.tenderwebclient.seleniumTests.type;

import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateTypePage {
    WebDriver driver;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "slug")
    private WebElement slugInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public UpdateTypePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UpdateTypePage updateName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    public UpdateTypePage updateSlug(String slug) {
        slugInput.clear();
        slugInput.sendKeys(slug);
        return this;
    }

    public TenderDetailsPage submitForm() {
        submitButton.click();
        return new TenderDetailsPage(driver);
    }
}
