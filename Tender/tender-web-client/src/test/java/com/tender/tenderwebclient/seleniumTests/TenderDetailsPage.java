package com.tender.tenderwebclient.seleniumTests;

import com.tender.tenderwebclient.seleniumTests.awarded.UpdateAwardedPage;
import com.tender.tenderwebclient.seleniumTests.purchaser.UpdatePurchaserPage;
import com.tender.tenderwebclient.seleniumTests.tender.DeleteTenderPage;
import com.tender.tenderwebclient.seleniumTests.tender.UpdateTenderPage;
import com.tender.tenderwebclient.seleniumTests.type.UpdateTypePage;
import org.openqa.selenium.By;
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

    @FindBy(css = "a[href*='/updatePurchaser']")
    private WebElement updatePurchaserButton;

    @FindBy(css = "a[href*='/updateAwarded']")
    private WebElement updateAwardedButton;

    @FindBy(css = "a[href*='/updateType']")
    private WebElement updateTypeButton;

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

    public UpdatePurchaserPage clickUpdatePurchaser() {
        updatePurchaserButton.click();
        return new UpdatePurchaserPage(driver);
    }

    public UpdateAwardedPage clickUpdateAwarded() {
        updateAwardedButton.click();
        return new UpdateAwardedPage(driver);
    }

    public UpdateTypePage clickUpdateType() {
        updateTypeButton.click();
        return new UpdateTypePage(driver);
    }

    public String getTenderTitle() {
        return driver.findElement(By.id("tender-title")).getText();
    }

    public String getTenderCategory() {
        return driver.findElement(By.id("tender-category")).getText();
    }

    public String getTypeName() {
        return driver.findElement(By.id("type-name")).getText();
    }

    public String getTypeSlug() {
        return driver.findElement(By.id("type-slug")).getText();
    }

    public String getAwardedValueForOne() {
        return driver.findElement(By.id("awarded-value-for-one")).getText();
    }

    public String getAwardedValueForTwo() {
        return driver.findElement(By.id("awarded-value-for-two")).getText();
    }

    public String getPurchaserSID() {
        return driver.findElement(By.id("purchaser-sid")).getText();
    }

    public String getPurchaserName() {
        return driver.findElement(By.id("purchaser-name")).getText();
    }

}
