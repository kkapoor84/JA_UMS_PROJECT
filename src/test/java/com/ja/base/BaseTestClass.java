package com.ja.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static com.ja.base.DriverSetup.*;

public class BaseTestClass {

	@BeforeSuite
	public void initStart() throws Exception {
		initialization();
		openURL();
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}
}
