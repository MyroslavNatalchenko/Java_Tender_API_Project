package com.tender.tenderwebclient.seleniumTests.awarded;

import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateAwardedPage {
    WebDriver driver;

    @FindBy(id = "valueForOne")
    private WebElement valueForOneInput;

    @FindBy(id = "valueForTwo")
    private WebElement valueForTwoInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public UpdateAwardedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UpdateAwardedPage updateValueForOne(String value) {
        valueForOneInput.clear();
        valueForOneInput.sendKeys(value);
        return this;
    }

    public UpdateAwardedPage updateValueForTwo(String value) {
        valueForTwoInput.clear();
        valueForTwoInput.sendKeys(value);
        return this;
    }

    public TenderDetailsPage submitForm() {
        submitButton.click();
        return new TenderDetailsPage(driver);
    }
}

