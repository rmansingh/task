package com.pages;

import com.framework.config.FileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.framework.Constants.PathParameters.OUR_OFFERS_PATH;
import static com.sun.activation.registries.LogSupport.log;

/**
 * @author Rupak Mansingh
 * this class captures the actions and elements of offers page
 */

public class OfferPage extends BasePage {

    @FindBy(id = "termFilter")
    public WebElement termDropdown;

    public OfferPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Navigate to offer page")
    public OfferPage navigateToOffersPage() {
        log("Navigate to offer page");
        driver.get(FileReader.getInstance().getConfigReader().getApplicationUrl() + OUR_OFFERS_PATH);
        checkSelectRegionPopup();
        return this;
    }

    @Step("Select term year from drop down")
    public OfferPage selectTermYear(String year) {
        log("Select term year from drop down");
        termDropdown.sendKeys(year);
        return this;
    }

    @Step("Get highest interest rate")
    public String getHighestInterestRate() {
        String highestInterestRate;
        List<Double> intRateList = new ArrayList<>();
        List<WebElement> interestRate = driver
                .findElements(By.xpath("//div[@class='OfferRowstyles__number--3PAX1']"));

        for (WebElement rate : interestRate) {
            findElement(rate);
            intRateList.add(Double.parseDouble(rate.getText().trim()));
        }
        // get the arraylist in descending order
        Collections.sort(intRateList, (I1, I2) -> (I1 > I2) ? -1 : (I1 < I2) ? 1 : 0);
        highestInterestRate = String.valueOf(intRateList.get(0));
        log("The highest interest rate is " + highestInterestRate);
        return highestInterestRate;
    }

    @Step("Click on register button")
    public RegistrationPage clickRegisterButton() {

        driver.findElement(By.xpath("//div[contains(text(),'" + getHighestInterestRate()
                + "')]/../../..//span[contains(text(),'Register now')]")).click();
        return new RegistrationPage(driver);
    }

    public int getBankOffersFromTerm() {
        Select sel = new Select(termDropdown);
        String strCurrentValue = sel.getFirstSelectedOption().getText();
        return getOffers(strCurrentValue);
    }

    public int getBanks() {
        List<WebElement> listOfInterestRate = driver.findElements(By.xpath("//div[@class='OfferRowstyles__number--3PAX1']"));
        return listOfInterestRate.size();
    }

    private int getOffers(String termYear) {
        Pattern p = Pattern.compile("[^\\d]*[\\d]+[^\\d]+([\\d]+)");
        Matcher m = p.matcher(termYear);
        if (m.find()) {
            return Integer.parseInt(m.group(1));
        }
        throw new NoSuchElementException("No such element found for offers");
    }
}
