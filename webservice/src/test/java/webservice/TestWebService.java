package webservice;

import static com.jayway.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;

public class TestWebService {

	@Test
	public void ShouldGetUserCheckStatus() {
		expect().statusCode(200).when().get("/users/1");
	}

	@Test
	public void testGetSingleUser() {
	  expect().
	    statusCode(200).
	    body(
	      "userId", equalTo("1"),
	      "firstName", equalTo("Nitesh"),
	      "lastName", equalTo("Kumar")).
	    when().
	    get("/users/1");
	}
	
	@Test
	public void testGetUserWithId1() {
	  Response res = (Response) get("/users/1");
	  assertEquals(200, res.getStatus());
	  String json = res.toString();
	  JsonPath jp = new JsonPath(json);
	  assertEquals("Nitesh", jp.get("firstName"));
	  assertEquals("Kumar", jp.get("lastName"));
	  assertEquals("1", jp.get("userId"));
	}
	
	@Test
	public void testGetUserWithId2() {
	  Response res = (Response) get("/users/2");
	  assertEquals(200, res.getStatus());
	  String json = res.toString();
	  JsonPath jp = new JsonPath(json);
	  assertEquals("Nits", jp.get("firstName"));
	  assertEquals("Kumar", jp.get("lastName"));
	  assertEquals("2", jp.get("userId"));
	}

}
