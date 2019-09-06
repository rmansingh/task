package com.raisin.test;

import com.pages.OfferPage;
import com.base.BaseTest;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static org.testng.Reporter.log;

/**
 * @Author: Rupak Mansingh
 * @Desc: Test case to validate log in activity
 */
public class OfferTest extends BaseTest {

    @Feature("Bank Offer feature")
    @Owner("Rupak Mansingh")
    @Test()
    public void OfferTest() {
        log("------------------- Starting OfferTest Test -------------------");
        OfferPage offerpage = new OfferPage(getDriver());
        offerpage.navigateToOffersPage()
                .clickTab();
//        assertThat("My account page is missing",actual,is(equalTo("expected")));
        log("------------------- Ending OfferTest Test -------------------");
    }
}
