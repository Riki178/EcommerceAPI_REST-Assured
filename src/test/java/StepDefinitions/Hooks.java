package StepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deleteProduct")
	public void before() throws IOException {
		
		StepDefinitions df = new StepDefinitions();
		if(df.productId == null) {
			df.Login_payload_with("deysayan1301@gmail.com");
			df.user_calls_with_request("LoginAPI", "post");
			df.user_extracts_login_credentials_from_response();
			df.productId = "6aa01b73e7cd69710fc9f31d";
		}
	}
	
	@AfterAll
	public static void afterAll() {
		
		System.out.println("Working Successfully");
	}
}