package com.tender.tenderwebclient.seleniumTests.tender;

import com.tender.tenderwebclient.seleniumTests.TenderDetailsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AllTendersPage {
    WebDriver driver;

    @FindBy(css = "table tr td a[href*='/TenderDetails']")
    private List<WebElement> tenderDetailsLinks;

    @FindBy(tagName = "h1")
    private WebElement header;

    public AllTendersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AllTendersPage open() {
        this.driver.get("http://localhost:8888/allTenders");
        return this;
    }

    public String getHeader() {
        return this.header.getText();
    }

    public TenderDetailsPage clickFirstTenderDetails() {
        if (!tenderDetailsLinks.isEmpty()) {
            tenderDetailsLinks.get(0).click();
        }
        return new TenderDetailsPage(driver);
    }

    public String getFirstTenderSourceId() {
        return driver.findElement(By.cssSelector(".tender-row .source-id")).getText();
    }

    public boolean isTenderPresent(String sourceId) {
        return driver.findElements(By.cssSelector(".tender-row[data-id='" + sourceId + "']")).size() > 0;
    }

    public String getLastTenderTitle() {
        List<WebElement> rows = driver.findElements(By.cssSelector(".tender-row .title"));
        return rows.get(rows.size() - 1).getText();
    }

}
