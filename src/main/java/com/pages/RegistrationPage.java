package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.framework.Constants.TimeOuts.TIME_OUT_FIVE_SECONDS;
import static com.sun.activation.registries.LogSupport.log;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of registration page
 */
public class RegistrationPage extends BasePage {

    @FindBy(xpath = "//*[@id='app']//*[@class='btn btn-primary']")
    public WebElement registerButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean isRegistrationPageDisplayed() {
        return isElementVisible(registerButton, TIME_OUT_FIVE_SECONDS);
    }

    public String getPageTile() {
        log("Get page tile ");
        return driver.getTitle();
    }
}