package com.ja.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import static com.ja.base.DriverSetup.*;

public class BaseTestClass {

	
	public BaseTestClass() {
		System.out.println("constructor of base class");
	}
	
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
