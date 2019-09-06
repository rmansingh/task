package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Rupak Mansingh
 * this class helps the activities of partner bank page
 * every action on the page is done here
 */

public class PartnerBankPage extends BasePage {

	@FindBy(id = "email_create")
	public WebElement txtNewEmail;

	public PartnerBankPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
}
