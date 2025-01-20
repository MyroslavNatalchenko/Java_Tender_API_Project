package com.tender.tenderwebclient.seleniumTests.tender.tests;

import com.tender.tenderwebclient.seleniumTests.tender.AddTenderPage;
import com.tender.tenderwebclient.seleniumTests.tender.AllTendersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTenderTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = new ChromeDriver();
    }

    @Test
    public void testAddForm() {
        String newSourceId = "11111111";
        String newTitle = "New Tender Title";

        AddTenderPage addTenderPage = new AddTenderPage(driver);
        addTenderPage.open()
                .fillSourceId(newSourceId)
                .fillDate("06.06.2006")
                .fillDeadlineDate("06.07.2006")
                .fillDeadlineLengthDays("30")
                .fillTitle(newTitle)
                .fillCategory("Category")
                .fillSid("2")
                .fillSourceUrl("https://example.com");
        AllTendersPage allTendersPage = addTenderPage.submitForm();

        String lastTenderTitle = allTendersPage.getLastTenderTitle();
        assertEquals(newTitle, lastTenderTitle);
    }


}
