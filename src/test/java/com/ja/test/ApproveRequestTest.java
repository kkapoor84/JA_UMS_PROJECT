package com.ja.test;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ja.base.BaseTestClass;
import com.ja.base.DriverSetup;
import com.ja.pages.AccountLoginPage;
import com.ja.pages.ApproveRequestPage;
import com.ja.utils.DataBaseUtil;
import com.ja.utils.Excel_To_Map;
import com.ja.utils.Excel_To_List;
import com.ja.utils.Property_File_Reader;
import com.ja.utils.RequestCourseAPI;

public class ApproveRequestTest extends BaseTestClass  {

	String classId;
	private AccountLoginPage alObject;
	private RequestCourseAPI apiObject;
	private DataBaseUtil dbObject;
	private ApproveRequestPage arObject;

	private static Map<String, String> data;
	private static List<String> headerList;
	private static List<String> requestFields;
	private static final String JA_AREA = "JAArea";
	private static final String Requestor = "Requestor";
	private static final String Requested_Class = "Requested Classes";

	@BeforeClass(alwaysRun = true)
	public void courseRequestAPI() throws Exception {
		
		data = Excel_To_Map.readExcel("JAClassTableData");
		headerList = Excel_To_List.excelToList("headerdata");
		requestFields = Excel_To_List.excelToList("Requestfieldsdata");

		dbObject = new DataBaseUtil();
		apiObject = new RequestCourseAPI();
		arObject = new ApproveRequestPage();
		alObject = new AccountLoginPage();
		classId = dbObject.getClassID();
	}

	@Test(description="Verification of API Post request and Login to UMS screen",priority=1,groups={"smoke","regression"})
	public void verifyPostAPIAndLogin() throws Exception {

		Assert.assertTrue(apiObject.httpPost(classId));
		alObject.loginSetup(Property_File_Reader.getvalue("usern"), Property_File_Reader.getvalue("passwd"));
	}

	@Test(description="Verified the request header title on Approve Request Screen",priority=2,dependsOnMethods = "verifyPostAPIAndLogin",groups={"smoke","regression"})
	public void verifyRequestHeaderInApproveRequest() throws Exception {

		Assert.assertTrue(arObject.courseApprove());
	}
 
	@Test(description="Verified the request fields",priority=3,dependsOnMethods ={"verifyRequestHeaderInApproveRequest"},groups ={"regression"})
	public void verifyRequestFieldsTitle() throws InterruptedException {
		Assert.assertTrue(arObject.courseRequestFieldsTitle(requestFields));
	}

	@Test(description="Verifed the number of course request",priority=4, dependsOnMethods = {"verifyRequestHeaderInApproveRequest"},groups={"smoke","regression"})
	public void verifyTotalNumberOfCourseRequest() throws InterruptedException {
		Assert.assertTrue(arObject.requestCount());
	}

	@Test(description="Verified the table header in Approve Request Screeen",priority=5,dependsOnMethods = {"verifyRequestHeaderInApproveRequest"},groups ={"regression"})

	public void verifyTableHeader() throws InterruptedException {

		Assert.assertTrue(arObject.tableHeader(headerList));
	}

	@Test(description="Verified the table data in Approve Request Screen",priority=6, dependsOnMethods = {"verifyRequestHeaderInApproveRequest"},groups={"smoke","regression"})
	public void verifyTableData() throws InterruptedException {

		Assert.assertTrue(arObject.tableValues(data.get(JA_AREA), data.get(Requestor), data.get(Requested_Class), "ja"));
	}

	@Test(description="Verified that Course Request is Approved",priority=7,dependsOnMethods = {"verifyRequestHeaderInApproveRequest"},groups={"smoke","regression"})
	public void verifyCourseRequestApproved() throws Exception {

		Assert.assertTrue(arObject.approveClassRequest());
	}

	@AfterClass(alwaysRun = true)
	public void deleteRecord1() {
	    dbObject.DeleteRecord(classId);
	}
}
