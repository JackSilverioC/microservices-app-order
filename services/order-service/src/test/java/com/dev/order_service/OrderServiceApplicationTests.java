package com.dev.order_service;

import com.dev.order_service.stubs.InventoryClientStub;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
class OrderServiceApplicationTests {

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldSubmitOrder() {
		String submitOrderJson = """
                {
                     "skuCode": "pixel_8",
                     "price": 4000,
                     "quantity": 1
                }
                """;
		InventoryClientStub.stubInventoryCall("pixel_8", 1);

		RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/orders")
				.then()
				.log().all()
				.statusCode(201)
				.body("orderNumber", notNullValue())
				.body("skuCode", equalTo("pixel_8"))
				.body("price", equalTo(4000))
				.body("quantity", equalTo(1));
	}

	@Test
	void shouldFailOrderWhenProductIsNotInStock() {
		String submitOrderJson = """
                {
                     "skuCode": "pixel_8",
                     "price": 4000,
                     "quantity": 1000
                }
                """;
		InventoryClientStub.stubInventoryCall("pixel_8", 1000);

		RestAssured.given()
				.contentType("application/json")
				.body(submitOrderJson)
				.when()
				.post("/api/orders")
				.then()
				.log().all()
				.statusCode(500);
	}

}
