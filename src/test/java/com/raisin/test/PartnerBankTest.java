package com.raisin.test;

import com.base.BaseTest;
import com.pages.PartnerBankPage;
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
 * @Desc: Test cases for invest on highest S&P Country rating
 */
@Owner("Rupak Mansingh")
@Feature("Partner bank features")
public class PartnerBankTest extends BaseTest {

    @DataProvider()
    protected Object[][] rating() {
        return new Object[][]{
                {"AA+"}
        };
    }

    @Test(dataProvider = "rating")
    @Description("Invest on highest S&P Country rating")
    public void investOnHighestSPCountryRating(String highestRating) {
        RegistrationPage registrationPage = new PartnerBankPage(getDriver())
                .navigateToPartnerBankPage()
                .clickBankWithHighestSAndPRating(highestRating)
                .clickInvestNow();

        assertThat("Registration page didn't displayed", registrationPage.isRegistrationPageDisplayed(), is(true));
        assertThat("Registration page tile didn't displayed", registrationPage.getPageTile(), is("Raisin Account Opening"));
    }
}
