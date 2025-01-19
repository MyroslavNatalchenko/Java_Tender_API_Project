package com.tender.tenderwebclient.seleniumTests.purchaser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.tender.tenderwebclient.seleniumTests.*;

public class UpdatePurchaserPage {
    WebDriver driver;

    @FindBy(id = "sid")
    private WebElement sidInput;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public UpdatePurchaserPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UpdatePurchaserPage updateSID(String sid) {
        sidInput.clear();
        sidInput.sendKeys(sid);
        return this;
    }

    public UpdatePurchaserPage updateName(String name) {
        nameInput.clear();
        nameInput.sendKeys(name);
        return this;
    }

    public TenderDetailsPage submitForm() {
        submitButton.click();
        return new TenderDetailsPage(driver);
    }
}

