package com.ja.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ja.base.BaseTestClass;
import com.ja.pages.AccountLoginPage;
import com.ja.utils.Excel_To_DataProvider;
import com.ja.utils.Property_File_Reader;

public class AccountLoginTest extends BaseTestClass {

	private AccountLoginPage alPage;

	@BeforeClass(alwaysRun = true)
	public void initDriver() throws Exception {
		alPage = new AccountLoginPage();
	}

	@Test(description = "Verification of login screen with invalid credentials", priority = 1, dataProvider = "Datafactory", dataProviderClass = Excel_To_DataProvider.class, groups = {
			"regression"} )
	public void verifyloginWithInValidCredentials(String uname, String pwd) throws Exception {

		alPage.loginSetup(uname, pwd);
		Assert.assertTrue(alPage.verifyInValidCredentials());
	}

	@Test(description = "Verification of login screen with valid credentails", priority = 2, groups = { "smoke",
			"regression" })
	public void verifyloginWithValidCredentials() throws Exception {

		alPage.loginSetup(Property_File_Reader.getvalue("usern"), Property_File_Reader.getvalue("passwd"));
		Assert.assertTrue(alPage.verifyValidCredentials());
		alPage.logout();
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		System.out.println("Test Class 'AccountLoginTest' test cases have been run successfully");
	}

}
