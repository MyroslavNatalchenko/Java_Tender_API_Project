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
        AddTenderPage addTender = new AddTenderPage(driver);
        addTender.open()
                .fillSourceId("5")
                .fillDate("24.07.2006")
                .fillDeadlineDate("24.08.2006")
                .fillDeadlineLengthDays("10")
                .fillTitle("popa")
                .fillCategory("pisya")
                .fillSid("4")
                .fillSourceUrl("pornoooo");
        AllTendersPage allTenders = addTender.submitForm();
        assertEquals("All Tenders", allTenders.getHeader());
    }

}
