package com.kayak.utilities;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CommonUtilities {

    static Properties props = new Properties();
    static FileInputStream fileIn = null;
    Logger log = Logger.getLogger(getClass().getSimpleName());

    public static String readfileReturnInString(String sPathOfJson) throws IOException {

        byte[] encoded = Files.readAllBytes(Paths.get(sPathOfJson));
        return new String(encoded, StandardCharsets.UTF_8);

    }

    public  void loadLog4jProperty(String sPropertiesFilePath) throws Exception {
        log.info("Log4j Property File Path: " + sPropertiesFilePath);
        fileIn = new FileInputStream(sPropertiesFilePath);
        props.load(fileIn);
        PropertyConfigurator.configure(props);

    }


    public static String takeScreenShot(WebDriver driver) throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        String addDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String destinationPath = System.getProperty("user.dir") + "/Report/Screenshots/"+ addDate+".PNG";
//        String destinationPath = System.getProperty("user.dir") + "/Report/SFDC_Report/Screenshots/"+ addDate+".PNG";
        File srcfile = screenshot.getScreenshotAs(OutputType.FILE);
        File dstfile = new File (destinationPath);
        FileUtils.copyFile(srcfile,dstfile);
//        extentTest.fail("Login to Homepage failed");
        return destinationPath;

    }
}
