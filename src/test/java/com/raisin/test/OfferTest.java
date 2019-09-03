package com.raisin.test;

import base.BaseClass;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import pages.OfferPage;

/**
 * @Author: Rupak Mansingh
 * @Desc: Test case to validate log in activity
 */
public class OfferTest extends BaseClass {

    @Feature("Bank Offer feature")
    @Owner("Rupak Mansingh")
    @Test()
    public void OfferTest() {
        log("------------------- Starting OfferTest Test -------------------");
        OfferPage offerpage = new OfferPage(driver);
        offerpage.navigateToOffersPage()
                .clickTab();
//        assertThat("My account page is missing",actual,is(equalTo("expected")));
        log("------------------- Ending OfferTest Test -------------------");
    }
}
