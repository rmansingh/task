package com.framework.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;
import java.util.Objects;

/**
 * @Author: Rupak Mansingh
 * @Desc: WebDriverFactory provides init method to initialize WebDriver for different os and browser combination
 */
public class WebDriverFactory {

   // public WebDriver driver;

    public static WebDriver init(String browser, String os) {
        return Browser.valueOf(browser.toUpperCase()).getDriver(os);
    }

    enum Browser {
        CHROME {
            public WebDriver getDriver(String os) {
                WebDriverManager manager = ChromeDriverManager.getInstance(DriverManagerType.CHROME);
                manager.setup();
//                System.setProperty("webdriver.chrome.driver", String.format("src/test/resources/drivers/%s/chromedriver_mac", os));
                return new ChromeDriver();
            }
        },
        FIREFOX {
            public WebDriver getDriver(String os) {
//                System.setProperty("webdriver.gecko.driver", String.format("src/test/resources/drivers/%s/geckodriver.exe", os));
                WebDriverManager manager = FirefoxDriverManager.getInstance(DriverManagerType.FIREFOX);
                manager.setup();
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.setCapability("marionette", true);
                return new FirefoxDriver();
            }
        };

        public abstract WebDriver getDriver(String os);
    }
}