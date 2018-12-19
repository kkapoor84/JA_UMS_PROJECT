package com.ja.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static com.ja.base.DriverSetup.*;

public class BaseTestClass {

	@BeforeSuite(alwaysRun = true)
	public void initStart() throws Exception {
		initialization();
		openURL();
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
