package com.ja.utils;

import static com.jayway.restassured.RestAssured.given;

import org.json.JSONException;
import org.testng.Assert;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class RequestCourseAPI {
	public boolean httpPost(String classId) throws JSONException, InterruptedException {

		System.out.println("***** Post API for course request fro BCRM********");
		// Initializing Rest API's URL
		String APIUrl = "http://182.71.186.218:7800/bcrm/api/sessionrequest/add?api_key=YmNybS1rZXk=";

		// Initializing payload or API body
		String APIBody = "[{\"ProgramName\": \"JA It's My Future 16-17\",\"ProgramGuid\": \"39926A3C-F8E6-DDED-B098-3377CFB91321\",\"ClassID\":"
				+ "\"" + classId + "\""
				+ ",\"RequestedSessions\": 1,\"SubmittedBy\": {\"AreaId\": \"101803\", \"LookupID\": \"kkapoor84\", \"FirstName\": \"k\", \"LastName\": \"kapoor\", \"EmailAddress\": \"khushboo.kapoor@3pillarglobal.com\" }}]";

		// System.out.println("Apibody " + APIBody);

		// Building request usng requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();

		// Setting API's body
		builder.setBody(APIBody.getBytes());

		// Setting content type as application/json or application/xml
		builder.setContentType("application/json; charset=UTF-8");

		RequestSpecification requestSpec = builder.build();

		// Making post request with authentication, leave blank in case there
		// are no credentials- basic("","")
		Response response = given().authentication().preemptive().basic("", "").spec(requestSpec).when().post(APIUrl);
		// JSONObject JSONResponseBody = new
		// JSONObject(response.body().asString());

		// Fetching the desired value of a parameter

		String result = response.body().asString();
		String expResult = "\"Thank you! Your request will be processed within the next 24 hours.\"";

		return result.equalsIgnoreCase(expResult);

	}

	public boolean httpPost2(String classId) throws JSONException, InterruptedException {
		System.out.println("******POST API to enroll user for the approved request****");
		// Initializing Rest API's URL
		String APIUrl = "http://182.71.186.218:7800/bcrm/api/UserEnrollment?api_key=YmNybS1rZXk=";

		String APIBody = "{\"ClassID\":" + "\"" + classId + "\""
				+ ",\"Users\":[{ \"Action\":\"ADD\", \"RoleID\":\"Teacher\", \"FirstName\":\"super\", \"LastName\":\"admin\", \"AreaId\":\"999999\", \"EmailAddress\":\"tom.landsness@ja.org\",\"LookupID\":\"superadmin\"},{\"Action\":\"ADD\", \"RoleID\":\"Teacher\", \"FirstName\":\"k\", \"LastName\":\"Volunteer\", \"AreaId\":\"101803\", \"EmailAddress\":\"k.Volunteer@3pillarglobal.com\", \"LookupID\":\"kVounteerlookup\"}],\"UpdatedBY\":{ \"AreaId\": \"101803\", \"LookupID\": \"super123admin\", \"FirstName\": \"super\", \"LastName\": \"admin\", \"EmailAddress\": \"tom.landsness@ja.org\"}}";

		// "{\"key1\":\"value1\",\"key2\":\"value2\"}";

		// Building request usng requestSpecBuilder
		RequestSpecBuilder builder = new RequestSpecBuilder();

		// Setting API's body
		builder.setBody(APIBody.getBytes());

		// Setting content type as application/json or application/xml
		builder.setContentType("application/json; charset=UTF-8");

		RequestSpecification requestSpec = builder.build();

		// Making post request with authentication, leave blank in case there
		// are no credentials- basic("","")
		Response response = given().authentication().preemptive().basic("", "").spec(requestSpec).when().post(APIUrl);
		// JSONObject JSONResponseBody = new
		// JSONObject(response.body().asString());

		// Fetching the desired value of a parameter

		String result = response.body().asString();
		String exp = "\"User enrollment done successfully\"";
		return result.equalsIgnoreCase(exp);

	}

}
