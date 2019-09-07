package com.base;

import com.TestFailureListener;
import com.framework.driver.WebDriverFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Rupak Mansingh
 * @Desc: Base class provides all the members & functions to be made visible for test classes and page objects
 */
@Listeners(TestFailureListener.class)
public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    String browser, os;

    private static final Logger log = Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod
    @Parameters({"browser", "os"})
    public void setUp(@Optional("chrome") String browser, @Optional("windows") String os) {
        /*Initializing log4j.*/
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        log("Initializing web driver.");
        this.browser = browser;
        this.os = os;
        driver = WebDriverFactory.init(browser, os);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10, 50);
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        closeBrowser();
    }

    private void closeBrowser() {
        driver.quit();
        log("Browser Closed");
    }

    /**
     * Logging method so that the same log is added in logger as well as in TestNG Report
     *
     * @param data
     */
    public void log(String data) {
        log.info(data);
        Reporter.log(data + "\n");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
