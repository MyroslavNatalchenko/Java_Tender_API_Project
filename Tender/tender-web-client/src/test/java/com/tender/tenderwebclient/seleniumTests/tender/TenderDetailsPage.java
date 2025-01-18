package com.tender.tenderwebclient.seleniumTests.tender;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TenderDetailsPage {
    WebDriver driver;

    @FindBy(css = "a[href*='/removeTender']")
    private WebElement deleteTenderButton;

    @FindBy(css = "a[href*='/updateTender']")
    private WebElement updateTenderButton;

    public TenderDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public DeleteTenderPage clickDeleteTender() {
        deleteTenderButton.click();
        return new DeleteTenderPage(driver);
    }

    public UpdateTenderPage clickUpdateTender() {
        updateTenderButton.click();
        return new UpdateTenderPage(driver);
    }
}
