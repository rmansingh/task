package com.base;

import com.TestFailureListener;
import com.framework.config.FileReader;
import com.framework.driver.WebDriverFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.DriverManager;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Rupak Mansingh
 * @Desc: Base class provides all the members & functions to be made visible for test classes and page objects
 */
@Listeners(TestFailureListener.class)
public class BaseTest {

    private static WebDriver driver;
    protected WebDriverWait wait;
    String browser, os;

    private static final Logger log = Logger.getLogger(BaseTest.class.getName());

    @BeforeMethod
    @Parameters({"browser", "os"})
    public void setUp(@Optional("chrome") String browser, @Optional("windows") String os) {
        /*Initializing log4j.*/
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        log("Getting url environment variable and setting default value.");
        String url = System.getProperty("url", FileReader.getInstance().getConfigReader().getApplicationUrl());

        log("Initializing webdriver.");
        this.browser = browser;
        this.os = os;
        driver = WebDriverFactory.init(browser, os);
        driver.manage().window().maximize();

        log("Navigating to Home page.");
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10, 50);
    }

    @AfterClass(alwaysRun = true)
    public void endTest() {
        closeBrowser();
    }

    private void closeBrowser() {
        driver.quit();
        log("Browser Closed");
    }

    /**
     * Method to be used for getting the status of the test execution
     */
 //   @AfterMethod
//    public  WebDriver getDriverManager() {
//        System.out.println("printing the driver: " + driver);
//        return driver;
//    }


    /**
     * Logging method so that the same log is added in logger as well as in TestNG Report
     *
     * @param data
     */
    public void log(String data) {
        log.info(data);
        Reporter.log(data + "\n");
    }

    /**
     * Common method to wait for visibility of elements in all test classes
     *
     * @param element
     * @return
     */
    public WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Function to parse test data from the json file
     */
    public JSONObject getTestData() {
        JSONObject testData = null;
        String testDataFilePath = "src\\test\\resources\\test-data\\ProductToBeCheckedout.json";
        try {
            InputStream is = new FileInputStream(new File(testDataFilePath));
            JSONTokener jsonTokener = new JSONTokener(is);
            testData = new JSONObject(jsonTokener);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return testData;
    }

    public WebDriver getDriver() {
        return driver;
    }
}
