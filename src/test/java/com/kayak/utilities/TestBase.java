package com.kayak.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.jayway.jsonpath.JsonPath;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class TestBase {
    public static String sBrowser;
    public static String sConfig;
    public static String sAutomation;
    WebDrManager driverManagerWeb = new WebDrManager();
    public static WebDriver driverWeb;

    public static Logger log = Logger.getLogger(TestBase.class.getSimpleName());
    public static SelUtility oSelUtil = new SelUtility();
    public static CommonUtilities oCommonUtil = new CommonUtilities();
    public static Properties properties;
    public static ExtentTest extentTest = null;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports = null;

    @BeforeSuite
    public void dependencyFulfillMent() throws Exception{
        oCommonUtil.loadLog4jProperty(System.getProperty("user.dir") + "/resources/testdata/Log4js2.properties");
        sConfig = CommonUtilities.readfileReturnInString(System.getProperty("user.dir") + "/resources/testdata/config.json");
        sAutomation = JsonPath.read(sConfig, "automation");
        properties = oSelUtil.getProperties();

        String addDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        extentReports = new ExtentReports();
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/resources/reports/"+addDate+".html"  );
        extentReports.attachReporter(htmlReporter);
        extentTest = extentReports.createTest("SFDC Web Automation Portal");


        if(sAutomation.toLowerCase().equals("web")){
            sBrowser = JsonPath.read(sConfig, "browser");
            driverWeb = oSelUtil.launchApp();
            log.info("driverWeb :- " + driverWeb);
        }

    }


    @AfterSuite
    public void terminateApp() throws Exception{
        if(sAutomation.toLowerCase().equals("web")){
            oSelUtil.quitApp();
        }
    }


}
