package com.tender.tenderwebclient.seleniumTests.tender;

import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdateTenderPage {
    WebDriver driver;

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(id = "category")
    private WebElement categoryInput;

    @FindBy(id = "deadlineDate")
    private WebElement deadlineDateInput;

    @FindBy(id = "submit")
    private WebElement submitButton;


    public UpdateTenderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public UpdateTenderPage updateTitle(String title) {
        titleInput.clear();
        titleInput.sendKeys(title);
        return this;
    }

    public UpdateTenderPage updateCategory(String category) {
        categoryInput.clear();
        categoryInput.sendKeys(category);
        return this;
    }
    public UpdateTenderPage updateDeadlineDate(String deadlineDate) {
        deadlineDateInput.clear();
        deadlineDateInput.sendKeys(deadlineDate);
        return this;
    }

    public TenderDetailsPage submitForm() {
        submitButton.click();
        return new TenderDetailsPage(driver);
    }
}
