import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.Validatable;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class ReqresAPI {

	int id;
	@Test
	public void gettListAllUsers() {
		
		when()
			.get("https://reqres.in/api/users?page=")
		.then()
			.statusCode(200);
			//.log().all();
	}

	@Test
	public void postCreateUser() {
		
		HashMap requestBody = new HashMap();
		requestBody.put("name" , "Aloha");
		requestBody.put("job", "teacher");
		
	 id=given()
			 .contentType("application/json")
			 .body(requestBody)
			
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
//		.then()
//			.statusCode(201)
//			.log().all();
	}

//	@Test
//	public void getSignleUser() {
//		
//		given()
//		
//		
//		.when()
//			.get("https://reqres.in/api/users/"+id)
//		
//		.then()
//			//.statusCode(200)
//			.log().all();
//	}

	@Test
	public void putUpdateUser() {
		
		HashMap requestBody = new HashMap();
		requestBody.put("name" , "Aloha");
		requestBody.put("job", "Programmer");
	
	given()
		.contentType("application/json")
		.body(requestBody)
	.when()
		.put("https://reqres.in/api/users/"+id)
	.then()
		.statusCode(200)	
		.log().all();
	}
	
	@Test
	public void deleteUser() {
		
	when()
		.delete("https://reqres.in/api/users/"+id)
	.then()
		.statusCode(204)	
		.log().all();
	}
		

	
}

