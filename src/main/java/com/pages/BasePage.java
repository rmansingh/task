package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{

	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10, 50);
		PageFactory.initElements(driver, this);
	}

	public WebElement waitForVisibilityOf(WebElement element) {
		return wait.until(ExpectedConditions.visibilityOf(element));
	}
//	public WebDriver getDriverManager() {
//		return driver;
//	}
}