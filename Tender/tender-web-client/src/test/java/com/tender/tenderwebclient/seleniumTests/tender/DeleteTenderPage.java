package com.tender.tenderwebclient.seleniumTests.tender;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DeleteTenderPage {
    WebDriver driver;

    @FindBy(id = "submit")
    private WebElement confirmDeleteButton;

    public DeleteTenderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AllTendersPage confirmDelete() {
        confirmDeleteButton.click();
        return new AllTendersPage(driver);
    }
}
