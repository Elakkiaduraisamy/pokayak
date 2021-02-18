package com.kayak.pomodel;

import com.kayak.utilities.TestBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class POSearch extends TestBase {
    WebDriver driver;
    public POSearch(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@id,'origin-airport')]")
    WebElement originpl;

    @FindBy(xpath = "//*[contains(@id, \"destination-airport-display\")]")
    WebElement destpl;

    @FindBy(xpath = "//*[contains(@id, \"origin-airport-display\")][1]")
    WebElement txtOriginpl;

    @FindBy(xpath = "//input[contains(@id, \"destination-airport\")]")
    WebElement txtDestnpl;

    @FindBy(xpath = "//*[@id=\"c5CWO-dateRangeInput-display-start\"]/div")
    WebElement departDate;

    @FindBy(xpath = "")
    WebElement returnDate;

    @FindBy(xpath = "//span[@class=\"icon \"]")
    WebElement searchBtn;

    @FindBy(xpath = "//div[contains(@class, 'resultWrapper')]//*[contains(@class, 'Flights-Results-FlightPriceSection')]")
    List<WebElement> searchResults;

    @FindBy(xpath = "//*[contains (@class,'Base-Results-HorizonResult')]")
    List<WebElement> searchResults2;


    public void searchFlights(int iSeconds) throws Exception {
//        oSelUtil.isClickable(iSeconds, originpl, driver);
//        oSelUtil.webElementClear(originpl);
        Thread.sleep(iSeconds);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("document.getElementById('mgIr-origin-code')[0].setAttribute('type', 'text');");
        oSelUtil.webElementClear(originpl);
        oSelUtil.webElementSendKeys(originpl, properties.getProperty("kayak.originpl"));
        log.info("Origin is entered");
        oSelUtil.isClickable(iSeconds, destpl, driver);
        oSelUtil.webElementSendKeys(destpl, properties.getProperty("kayak.destnpl"));
        log.info("Destination is entered");
        oSelUtil.waitExplicitly(10, originpl, driver);
//        oSelUtil.webElementClick(loginButton);
//        log.info("Login Button Clicked");
//        oSelUtil.waitExplicitly(5, userNavBtn, driver);
    }
    public void searchFlights2(int iSeconds) throws Exception {
        Thread.sleep(iSeconds);
        oSelUtil.waitExplicitly(10,originpl,driver);
        oSelUtil.webElementClick(originpl);
        oSelUtil.webElementSendKeys(txtOriginpl, properties.getProperty("kayak.originpl"));
        log.info("Origin is entered");
        oSelUtil.webElementClick(destpl);
        oSelUtil.webElementSendKeys(txtDestnpl, properties.getProperty("kayak.destnpl"));
        log.info("Destination is entered");

    }


}
