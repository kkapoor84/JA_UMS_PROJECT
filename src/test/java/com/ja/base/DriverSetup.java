package com.ja.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ja.utils.Property_File_Reader;

public class DriverSetup {

	public static WebDriver driver;
	public static Property_File_Reader pfObject;

	public static void initialization() throws Exception {

		pfObject = new Property_File_Reader();

		if (pfObject.getvalue("Browser").equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (pfObject.getvalue("Browser").equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (pfObject.getvalue("Browser").equalsIgnoreCase("ie")) {

			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

			ieCapabilities.setCapability("nativeEvents", false);
			ieCapabilities.setCapability("ignoreZoomSetting", true);
			ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			ieCapabilities.setCapability("enablePersistentHover", true);

			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir") + "/src/test/resources/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(ieCapabilities);
		}

	}

	public static void openURL() {

		driver.get(pfObject.getvalue("URL"));
		driver.manage().window().maximize();
		System.out.println("Welcome To JA Application");
	}

}
