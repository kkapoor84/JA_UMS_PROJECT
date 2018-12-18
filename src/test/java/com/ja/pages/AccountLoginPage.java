package com.ja.pages;

import static com.ja.base.DriverSetup.driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class AccountLoginPage {
	private static Logger logger = LogManager.getLogger(com.ja.pages.AccountLoginPage.class);

	public static By uname = By.id("UserName");
	public static By pwd = By.id("Password");
	public static By login = By.name("btnSubmit");
	public static By HomeLink = By.xpath(".//*[contains(text(),'Home')]");
	public static By Arrow = By.xpath(".//div[text()='super admin']/following-sibling::div");
	public static By Logout = By.xpath(".//*[contains(text(),'Log Out')]");
	public static By ErrorMessage = By.xpath("//div[starts-with(@class,'error-msg')]//ul/li");

	public void loginSetup(String username, String userpassword) throws Exception {

		driver.navigate().refresh();
		driver.findElement(uname).clear();
		logger.info("Clear the username field");
		driver.findElement(pwd).clear();
		logger.info("Clear the password field");
		Thread.sleep(2000);
		driver.findElement(uname).sendKeys(username);
		logger.info("Enter username");
		Thread.sleep(2000);
		driver.findElement(pwd).sendKeys(userpassword);
		logger.info("Enter password");
		Thread.sleep(2000);
		driver.findElement(login).click();
		logger.info("Click Login button");

	}

	public boolean verifyInValidCredentials() {

		boolean IsInvalidCredential = false;

		String ActualErrorMessage = driver.findElement(ErrorMessage).getText();
		String ExpectedErrorMessage = "The User Name or Password you have entered is invalid";

		if (ActualErrorMessage.equalsIgnoreCase(ExpectedErrorMessage)) {
			IsInvalidCredential = true;
		}
		return IsInvalidCredential;
	}

	public boolean verifyValidCredentials() throws InterruptedException {
		Thread.sleep(5000);
		boolean IsValidCredential = false;
		String ActualValue = driver.findElement(HomeLink).getText();
		logger.info("Text of Home link on home page is:" + ActualValue);
		if (ActualValue.contains("Home")) {
			logger.info("Home text is available on home page");
			IsValidCredential = true;
		}
		return IsValidCredential;
	}

	public void logout() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(Arrow).click();
		Thread.sleep(2000);
		driver.findElement(Logout).click();
		logger.info("User is Logged out and Application is closed | ");

	}

}
