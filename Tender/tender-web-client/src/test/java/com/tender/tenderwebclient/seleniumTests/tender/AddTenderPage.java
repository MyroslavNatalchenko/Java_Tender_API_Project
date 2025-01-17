package com.tender.tenderwebclient.seleniumTests.tender;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddTenderPage {
    WebDriver driver;

    @FindBy(id = "sourceId")
    private WebElement sourceIdInput;

    @FindBy(id = "date")
    private WebElement dateInput;

    @FindBy(id = "deadlineDate")
    private WebElement deadlineDateInput;

    @FindBy(id = "deadlineLengthDays")
    private WebElement deadlineLengthDaysInput;

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(id = "category")
    private WebElement categoryInput;

    @FindBy(id = "sid")
    private WebElement sidInput;

    @FindBy(id = "sourceUrl")
    private WebElement sourceUrlInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    public AddTenderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AddTenderPage open() {
        this.driver.get("http://localhost:8888/addTender");
        return this;
    }

    public AddTenderPage fillSourceId(String sourceId) {
        this.sourceIdInput.clear();
        this.sourceIdInput.sendKeys(sourceId);
        return this;
    }

    public AddTenderPage fillDate(String date) {
        this.dateInput.sendKeys(date);
        return this;
    }

    public AddTenderPage fillDeadlineDate(String deadlineDate) {
        this.deadlineDateInput.sendKeys(deadlineDate);
        return this;
    }

    public AddTenderPage fillDeadlineLengthDays(String deadlineLengthDays) {
        this.deadlineLengthDaysInput.sendKeys(deadlineLengthDays);
        return this;
    }

    public AddTenderPage fillTitle(String title) {
        this.titleInput.sendKeys(title);
        return this;
    }

    public AddTenderPage fillCategory(String category) {
        this.categoryInput.sendKeys(category);
        return this;
    }

    public AddTenderPage fillSid(String sid) {
        this.sidInput.sendKeys(sid);
        return this;
    }

    public AddTenderPage fillSourceUrl(String sourceUrl) {
        this.sourceUrlInput.sendKeys(sourceUrl);
        return this;
    }

    public ViewAllTenders submitForm() {
        this.submitButton.click();
        return new ViewAllTenders(driver);
    }
}
