package com.ja.pages;

import static com.ja.base.DriverSetup.driver;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.ja.utils.DateUtilility;

public class ApproveRequestPage {

	private static Logger logger = LogManager.getLogger(com.ja.pages.ApproveRequestPage.class);

	public static By ApproveRequestLink = By.xpath(".//a[text()='Approve Requests']");

	public static By Request = By.cssSelector("[class*=glyphicon-triangle-right]");

	public static By CourseRequestText = By.cssSelector("[class*=dw-name]");

	public static By CourseCheckBox = By.cssSelector("[class=text-center]>input[type=checkbox]");

	public static By ApproveButton = By.xpath("//button[text()[contains(.,'Approve')]]");

	public static By CrossPopUp = By.xpath("(.//div[@id='successBox']//img[@alt='User Picture'])[1]");

	public static By PopYes = By.cssSelector("button[id='btnOk']");

	public static By TableColumn = By.cssSelector("[id='dvtbl']>table th[class='sorting']");

	public static By totalRows = By.xpath("//*[@id='dvtbl']/table/tbody/tr");

	public static By RequestFields = By.xpath("//*[@class='dw-name']/parent::div/following-sibling::div/span[1]");

	public static By totalrequestcount = By.xpath("//*[@class='dw-name']/parent::div/following-sibling::div[1]/span[2]");

	public static By oldestrequestcount = By.xpath("//*[@class='dw-name']/parent::div/following-sibling::div[2]/span[2]");

	public static By searchtext = By.xpath("(//*[@id='dvtbl']//input[@type='text'])[1]");

	public boolean courseApprove() throws InterruptedException, IOException {
		Thread.sleep(2000);
		logger.info("******Go to My Course->Approve Request to approve the request****");
		driver.findElement(ApproveRequestLink).click(); 
		Thread.sleep(200);
		String ActualResult = driver.findElement(CourseRequestText).getText();
		String ExpectedResult = "JA It's My Future 16-17";
		driver.findElement(Request).click();
		return ActualResult.equalsIgnoreCase(ExpectedResult);

	}

	public boolean courseRequestFieldsTitle(List<String> a) throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> TableRow = driver.findElements(RequestFields);

		boolean IsTitleMatched = true;

		for (int i = 0; i < TableRow.size(); i++) {

			String Actual = TableRow.get(i).getText().trim();
			String Expected = a.get(i);
			if (!Expected.equalsIgnoreCase(Actual)) {
				IsTitleMatched = false;
				break;
			}
		}
		return IsTitleMatched;
	}

	public boolean requestCount() throws InterruptedException {
		String expcurrentdatee = DateUtilility.getCurrentDateFormatted();

		WebElement oldrequestcount = driver.findElement(oldestrequestcount);
		String oldrequestDate = oldrequestcount.getText().trim();

		WebElement ele = driver.findElement(totalrequestcount);
		String reqcount = ele.getText();

		int reqcountint = Integer.parseInt(reqcount);

		boolean IsSingleRow = false;
		List<WebElement> requestrow = driver.findElements(totalRows);
		Thread.sleep(2000);

		int Expected_rowcount = 1;
		int Actual_rowcount = requestrow.size();

		if ((Expected_rowcount == Actual_rowcount) && (Expected_rowcount == reqcountint)
				&& expcurrentdatee.contains(oldrequestDate)) {

			IsSingleRow = true;
		}
		logger.info("Course Request Exist" + Actual_rowcount + "Total Request Exist" + reqcountint + "Oldest Request Exist on Date" + oldrequestcount.getText());
		return IsSingleRow;

	}

	public boolean tableHeader(List<String> a) throws InterruptedException {
		Thread.sleep(2000);
		List<WebElement> TableRow = driver.findElements(TableColumn);

		boolean IsTableHeaderMatched = true;

		for (int i = 0; i < TableRow.size(); i++) {

			String Actual = TableRow.get(i).getText().trim();
			String Expected = a.get(i);

			if (!Expected.equalsIgnoreCase(Actual)) {
				IsTableHeaderMatched = false;
				break;
			}
		}
		return IsTableHeaderMatched;

	}

	public boolean tableValues(String theader1, String theader2, String theader3, String AreaName)throws InterruptedException {
		WebElement SearchTextInTB = driver.findElement(searchtext);
		
		Actions A1 = new Actions(driver);
		Action seriesofaction1 = A1.moveToElement(SearchTextInTB).click().keyDown(SearchTextInTB, Keys.SHIFT)
				.sendKeys(SearchTextInTB, AreaName).keyUp(SearchTextInTB, Keys.SHIFT).build();
		seriesofaction1.perform();
		
		logger.info("Entered the text in search box");
		SearchTextInTB.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		SearchTextInTB.clear();

		String expcurrentdate = DateUtilility.getCurrentDateFormatted();
		boolean isRowPresent = false;
		Thread.sleep(2000);
		List<WebElement> noOfrows = driver.findElements(totalRows);

		for (WebElement row : noOfrows) {
			String rowText = row.getText();
			if (rowText.contains(theader1) && rowText.contains(theader2) && rowText.contains(theader3)
					&& rowText.contains(expcurrentdate)) {
				isRowPresent = true;
			}

		}
		return isRowPresent;

	}

	public boolean approveClassRequest() throws InterruptedException, IOException {

		Thread.sleep(2000);
		driver.findElement(CourseCheckBox).click(); // Select Courses
		Thread.sleep(2000);
		driver.findElement(ApproveButton).click(); // Approve Courses.
		Thread.sleep(2000);
		driver.findElement(PopYes).click(); // Yes button
		Thread.sleep(2000);
		driver.findElement(CrossPopUp).click();
		logger.info("\n Course Request Approved");
		Thread.sleep(2000);
		boolean islistpresent = false;
		List<WebElement> totalrowofdata = driver.findElements(totalRows);
		int sizeofrow = totalrowofdata.size();
		int expectedrecord = 0;
		int actualrecord = sizeofrow;
		if (expectedrecord == actualrecord) {
			islistpresent = true;
		}

		return islistpresent;

	}

}
