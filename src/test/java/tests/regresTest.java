package tests;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class regresTest {

	@Test
	public void createUserTest() {
		JSONObject request = new JSONObject();
		request.put("name", "name");
		request.put("job", "job");

		baseURI = "https://reqres.in/api";

		Response response = given().header("Content-Type", "application/json").contentType(ContentType.JSON)
				.accept(ContentType.JSON).body(request.toJSONString()).when().post("/users").then().statusCode(201)
				.extract().response();

		String ID = response.path("id");

		System.out.println("The returned id is " + ID);
	}

    @Test
	public void getTest() {
		baseURI = "https://reqres.in/api";
		Response response = get("/users/7");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		String firstName = response.path("data.first_name");
		String lastName = response.path("data.last_name");
		System.out.println("The user with ID #7 is " + firstName + " " + lastName);
	}
}
