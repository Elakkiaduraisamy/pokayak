package com.kayak.utilities;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class SelUtility extends TestBase {


    public static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/resources/testdata/application.properties");
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    WebDrManager driverManagerWeb = new WebDrManager();
    Logger log = Logger.getLogger(getClass().getSimpleName());


    public Properties getProperties() {
        return properties;
    }
    public WebDriver launchApp() {
        driverManagerWeb.launchApp(sBrowser);
        return driverManagerWeb.getDriver();
    }

    public void goToKayakUrl() {
        driverWeb = driverManagerWeb.getDriver();
        log.info("Inside SelUtility : WebDriver " + driverWeb);
        driverWeb.get(properties.getProperty("kayak.url"));
    }

    public void waitExplicitly(int iSeconds, WebElement ele, WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, iSeconds);
        wait.ignoring(NoSuchElementException.class, ElementNotVisibleException.class);
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public boolean isClickable(int iSeconds, WebElement ele, WebDriver driver)
    {
        try{
            WebDriverWait wait = new WebDriverWait(driver, iSeconds);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            return ele.isDisplayed() && ele.isEnabled();
        }
        catch (Exception e){
            return false;
        }
    }

    public void quitApp() {
        extentReports.flush();
        driverManagerWeb.quitApp();
    }


    public void printListOfWebElements(List<WebElement> li, boolean bPrintSingleLine) throws Exception {
        for (int count = 0; count < li.size(); count++) {
            if (bPrintSingleLine)
                log.info(li.get(count).getText() + "\t");
            else
                log.info(li.get(count).getText());
        }
        if (bPrintSingleLine)
            log.info("blank Line");
    }

    public void waitTillDisplayUsingHardWait(String xpath) throws Exception {
        int count = 0;
        boolean bResFlag = true;
        while (bResFlag) {
            try {
                if (count == 10) {
                    bResFlag = false;
                }
                driverWeb.findElement(By.xpath(xpath)).isDisplayed();
                bResFlag = false;
            } catch (Exception e) {
                count++;
                Thread.sleep(1000);
            }
        }


    }

    public Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/resources/testdata/"+fileName);
        properties.load(fis);
        return  properties;
    }

    public void webElementClear(WebElement ele) {
        ele.clear();
    }

    public void webElementSendKeys(WebElement ele, String sValue) {
        ele.sendKeys(sValue);
    }

    public void webElementClick(WebElement ele) {
        ele.click();
    }

    public void verifyText(String ActualText, String ExpectedText, String msg) throws IOException {
        if (ActualText.equals(ExpectedText)) {
            log.info(msg + "is verified successfully");
        } else {
            log.info(msg + " verification is failed");
//    /        test.addScreenCaptureFromPath(takeScreenshot());
        }
    }
    public void verifyListOptions(Set<String> expectedList, List<WebElement> actualList, String msg){
        for (WebElement each: actualList){
            if(!expectedList.contains(each.getText())){
                String failMessage = msg + ":  not equal as the expected list";
                log.error(failMessage);
                break;
            }
        }
        log.info(msg + "is verified successfully againt the expected list");
    }
}

