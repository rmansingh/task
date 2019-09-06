package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Rupak Mansingh
 * this class helps the activities on registartion page
 */
public class RegistrationPage extends BasePage {

	@FindBy(id = "email_create")
	public WebElement txtNewEmail;

	public RegistrationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
}