package com;

import com.base.BaseTest;
import com.framework.driver.WebDriverFactory;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.nonNull;

@Slf4j
public class TestFailureListener extends TestListenerAdapter {

    private static final String TARGET_DIRECTORY = System.getProperty("target.dir");
    private static final String DS = System.getProperty("file.separator");
    private static final String SCREENSHOT_DIRECTORY = TARGET_DIRECTORY + DS + "screenshots";
    private static final String SCREENSHOT_EXTENSION = ".png";
   BaseTest baseTest = new BaseTest();

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //not able to get the driver here
            captureScreenshot(getDriverInstance(), result);
        }
    }

//    /**
//     * Method used for getting the screen capture with the name in a particular format
//     * @param methodName
//     */
//    public void takeScreenShot(String methodName) {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
//        try {
//            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//
//            String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath()
//                    + "/src/test/resources/failure_screencaptures/";
//            File destFile = new File(String.format("%sFailure_ScreenCaptures_%s_%s_%s_%s.png", reportDirectory, os, browser, methodName, formater.format(calendar.getTime())));
//            FileHandler.copy(srcFile, destFile);
//            Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
//                    + "' height='100' width='100'/> </a");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    private void captureScreenshot(WebDriver driver, ITestResult iTestResult) {
        byte[] screenshot = attachScreenShot(driver);
        if (nonNull(screenshot)) {
            saveToFile(screenshot, getFileName(iTestResult.getName()));
        } else {
            log.error("Something went wrong, screenshot was not taken");
        }
    }

    private byte[] getScreenShot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            log.error("Selenium screenshot capture failed: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Attachment(value = "Screen shot", type = "image/png", fileExtension = ".png")
    private byte[] attachScreenShot(WebDriver driver) {
        return getScreenShot(driver);
    }

    private void saveToFile(byte[] bytes, String fileName) {
        try {
            FileUtils.writeByteArrayToFile(new File(fileName), bytes);
        } catch (IOException e) {
            log.error("There was an issue creating the screenshot {}: {}", fileName, e.getMessage());
            e.printStackTrace();
        }
    }

    private WebDriver getDriverInstance() {
        return WebDriverFactory.init("chrome","mac");
       // return baseTest.getDriver();
    }

    private static String getFileName(String methodName) {
        return SCREENSHOT_DIRECTORY + DS + new SimpleDateFormat("dd-MM-yyyy_HH-mm-sss").format(new Date()) + "_" + methodName + SCREENSHOT_EXTENSION;
    }
}
