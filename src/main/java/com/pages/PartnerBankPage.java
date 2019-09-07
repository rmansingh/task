package com.pages;

import com.framework.config.FileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.NoSuchElementException;

import static com.framework.Constants.PathParameters.PARTNER_BANK_PATH;
import static com.sun.activation.registries.LogSupport.log;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of partner bank page
 */

public class PartnerBankPage extends BasePage {

    @FindBy(xpath = "//*[@id='sticky-wrapper']//a[contains(text(),'Invest now')]")
    public WebElement investNowButton;

    public PartnerBankPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Navigate to partner bank page")
    public PartnerBankPage navigateToPartnerBankPage() {
        log("Navigate to partner bank page");
        driver.get(FileReader.getInstance().getConfigReader().getApplicationUrl() + PARTNER_BANK_PATH);
        checkSelectRegionPopup();
        return this;
    }

    @Step("Find highest S&P rating and click on bank")
    public PartnerBankPage clickBankWithHighestSAndPRating(String highestRating) {
        log("Find highest S&P rating " + highestRating +"and click on bank");
        List<WebElement> listOfBank = driver.findElements(By.xpath("//*[@id='banks-archive-item-wrapper-cnt']//*[@class='bank-archive-item-title']"));
        int index = getHighestSAndPRating(highestRating);
        findElement(listOfBank.get(index)).click();
        return this;
    }

    @Step("Click invest now button")
    public RegistrationPage clickInvestNow() {
        log("Click invest now button");
        investNowButton.click();
        return new RegistrationPage(driver);
    }

    private int getHighestSAndPRating(String highestRating) {
        List<WebElement> listOfRating = driver.findElements(By.xpath("//*[@class='bank-archive-item-country-rating-score']"));
        for (int index = 0; index < listOfRating.size(); index++) {
            findElement(listOfRating.get(index));
            if (listOfRating.get(index).getText().trim().equalsIgnoreCase(highestRating)) {
                log("Index of highest S&P rating : " + index);
                return index;
            }
        }
        throw new NoSuchElementException("No such element found for S&P rating");
    }
}
