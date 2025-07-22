package com.xiuxiu.microservices.product;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestProductServiceApplication {
	@ServiceConnection
	static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI="http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();

	}
	@Test
	public void shouldCreateProduct() {
		String requestBody= """
				{
				    "name":"iPhone 16",
				    "description":"iPhone 16 is a smartphone from Apple",
				    "price":1000
				}
			""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id",Matchers.notNullValue())
				.body("name", Matchers.equalTo("iPhone 16"))
				.body("description",Matchers.equalTo("iPhone 16 is a smartphone from Apple"))
				.body("price",Matchers.equalTo(1000));
	}

}
