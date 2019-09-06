package com.pages;

import com.framework.config.FileReader;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.sun.activation.registries.LogSupport.log;


/**
 * @author Rupak Mansingh
 * this class helps the activities of partner bank page
 * every action on the page is done here
 */

public class OfferPage extends BasePage {

    @FindBy(id = "nav-main-link")
    public WebElement OfferTab;

    public OfferPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    @Step("Navigate to offer page")
    public OfferPage navigateToOffersPage() {
        //log("Navigate to offer page");
        driver.get(FileReader.getInstance().getConfigReader().getApplicationUrl() + "our-offers/");
        return this;
    }

    @Step("Blank test")
    public void clickTab() {
        log("Navigate to offer page");
        OfferTab.click();
    }
}