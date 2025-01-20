package com.tender.tenderwebapi.UrlTasksTest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.IsNull.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceUrlTest {
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void getAllSuppliersID() {
        RestAssured
                .get("/tenders/SupplierID")
                .then()
                .statusCode(200)
                .body("", notNullValue());
    }

    @Test
    @Order(2)
    public void getAllTendersID() {
        RestAssured
                .get("/tenders/TenderID")
                .then()
                .statusCode(200)
                .body("", notNullValue());
    }

    @Test
    @Order(3)
    public void getAllPurchaserID() {
        RestAssured
                .get("/tenders/PurchaserID")
                .then()
                .statusCode(200)
                .body("", notNullValue());
    }
}
