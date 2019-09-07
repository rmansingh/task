package com.raisin.test;

import com.base.BaseTest;
import com.pages.OfferPage;
import com.pages.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @Author: Rupak Mansingh
 * @Desc: Test cases for offer page
 */
@Owner("Rupak Mansingh")
@Feature("Raisin offers feature")
public class OfferTest extends BaseTest {

    @DataProvider()
    protected Object[][] yearTerm() {
        return new Object[][]{
                {"1 Year"}
        };
    }

    @Test(dataProvider = "yearTerm")
    @Description("Verify that dropdown option name contains correct amount of offers that have been displayed for 1 year option")
    public void verifyOffersWithTermDropDown(String year) {
        OfferPage offerpage = new OfferPage(getDriver())
                .navigateToOffersPage()
                .selectTermYear(year);

        assertThat("Number of offers didn't match with term drop down options", offerpage.getPartnerBankOffers(), is(offerpage.getPartnerBankOffersFromTermDropDown()));
    }

    @Test(dataProvider = "yearTerm")
    @Description("Find highest Interest rate in the list and click register now")
    public void registerNowOnHighestInterestRate(String year) {
        RegistrationPage registrationPage = new OfferPage(getDriver())
                .navigateToOffersPage()
                .selectTermYear(year)
                .clickRegisterButton();

        assertThat("Registration page didn't displayed", registrationPage.isRegistrationPageDisplayed(), is(true));
    }
}
