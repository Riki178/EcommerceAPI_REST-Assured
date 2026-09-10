package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;

import Resouces.APIResources;
import Resouces.TestData;
import Resouces.Utils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions extends Utils{
	
	RequestSpecification reqspec;
	Response response;
	public static String token;
	public static String userId;
	public static String productId;
	public static String orderId;
	
	@Given("Login payload with {string}")
	public void Login_payload_with(String userId) throws IOException {
	    
	    reqspec = given().spec(requestSpecification()).
	    		body(TestData.payload_login(userId));
	}

	@When("user calls {string} with {string} request")
	public void user_calls_with_request(String resource, String request) {
	    
		APIResources resourceAPI = APIResources.valueOf(resource);
		
		if(request.equalsIgnoreCase("POST")) {
			response = reqspec.when().post(resourceAPI.getResouce());
		}
		else if(request.equalsIgnoreCase("GET")) {
			response = reqspec.when().get(resourceAPI.getResouce());
		}
		else if(request.equalsIgnoreCase("PUT")) {
			response = reqspec.when().put(resourceAPI.getResouce());
		}
		else {
			response = reqspec.when().delete(resourceAPI.getResouce());
		}
		System.out.println(resourceAPI.getResouce());
		//System.out.println(response.asString());
	}

	@Then("user got the success status code {int}")
	public void user_got_the_success_status_code(Integer code) {
	    
		int sts_code = response.getStatusCode();
		Assert.assertEquals(sts_code, code);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	    
		JsonPath js = new JsonPath(response.asString());
		Assert.assertEquals(js.get(key), value);
		String msg = js.get(key);
	}
	
	@Then("user extracts login credentials from response")
	public void user_extracts_login_credentials_from_response() {
	    
		JsonPath js = new JsonPath(response.asString());
		token = js.get("token");
		userId = js.get("userId");
	}
	
	@Given("Create product payload")
	public void createproduct_payload() throws IOException {
	    
		reqspec = given().spec(requestSpecification()).header("Authorization",token).contentType(ContentType.MULTIPART).param("productName", "Laptop")
				.param("productAddedBy",userId).param("productCategory", "Laptop")
				.param("productSubCategory", "Dell").param("productPrice", "11500")
				.param("productDescription", "i5 10th").param("productFor", "men")
				.multiPart("productImage", new File("C:\\Users\\ricke\\OneDrive\\Desktop\\REST Assured Practice\\EcommerceAPI\\src\\test\\java\\ProductImage\\laptop.jpg"));
	}
	
	@Then("user get the productID")
	public void user_get_the_product_id() {
	    
		JsonPath js = new JsonPath(response.asString());
		productId = js.getString("productId");
		//System.out.println("productID - "+productId);
	}
	
	@Given("Place order payload with {string}")
	public void place_order_payload_with(String country) throws IOException {
	    
		reqspec = given().spec(requestSpecification()).header("Authorization",token)
				.body(TestData.payload_placeOrder(country));
	}
	
	@Given("User  gets the orderID")
	public void user_gets_the_order_id() {
	    
		JsonPath js = new JsonPath(response.asString());
		orderId = js.getString("orders[0]");
		//System.out.println("OrderID - "+orderID);
	}
	
	@Given("Delete order payload")
	public void delete_order_payload() throws IOException {
	    
		 reqspec = given().spec(requestSpecification()).header("Authorization",token).pathParam("orders", orderId);
	}
	
	@Given("Delete product payload")
	public void delete_product_payload() throws IOException {
	    
		reqspec = given().spec(requestSpecification()).header("Authorization", token).pathParam("productOrderId", productId);
	}
}
