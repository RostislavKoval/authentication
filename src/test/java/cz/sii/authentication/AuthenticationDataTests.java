package cz.sii.authentication;

import cz.sii.authentication.entity.AuthenticationData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthenticationDataTests
{
	@Autowired
	WebApplicationContext wac;

	@Test
	void create()
	{
		Map<String, String> map = new HashMap<>();
		map.put("attr1", "val1");
		map.put("attr2", "val2");

		AuthenticationData data = new AuthenticationData(
			10L,
			"type",
			"actor",
			map
		);

		given().
			webAppContextSetup(wac).
			contentType(JSON).
			body(data).
		when().
			post("/api/authentication_data").
		then().
			status(HttpStatus.CREATED).
			body("timestamp", equalTo(10)).
			body("type", equalTo("type")).
			body("actor", equalTo("actor")).
			body("transactionData.attr1", equalTo("val1")).
			body("transactionData.attr2", equalTo("val2"));
	}
}
