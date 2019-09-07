package com.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.sun.activation.registries.LogSupport.log;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @FindBy(xpath = "//button[contains(text(),'OK')]")
    public WebElement selectRegionSubmitButton;

    @FindBy(id = "field-country_choice")
    public WebElement countryDropDown;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10, 50);
        PageFactory.initElements(driver, this);
    }

    public WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Select region other from the drop down")
    public void checkSelectRegionPopup() {
        log("Select region other from the drop down");
        countryDropDown.sendKeys("other");
        waitForVisibilityOf(selectRegionSubmitButton);
        selectRegionSubmitButton.click();
    }

    @Step("Find element: {element}")
    protected WebElement findElement(WebElement element) {
        return this.wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Find element {element}")
    protected WebElement findElement(WebElement element, int timeOutInSeconds) {
        return (new WebDriverWait(this.driver, (long) timeOutInSeconds, 50L)).withMessage(String.format("Timed out waiting for: %s", element.toString())).until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Is element visible {element}")
    protected boolean isElementVisible(WebElement element, int timeOutInSeconds) {
        try {
            this.findElement(element, timeOutInSeconds);
            return true;
        } catch (WebDriverException var1) {
            return false;
        }
    }
}