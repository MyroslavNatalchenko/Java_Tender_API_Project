package com.tender.tenderwebapi.UrlTasksTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AwardedUrlTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void getAwardedByTenderId() {
        long tenderId = 831109;

        RestAssured
                .get("/tenders/AwardedTender/" + tenderId)
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(2)
    public void getAwardedBySupplierId() {
        long supplierId = 194251;

        RestAssured
                .get("/tenders/AwardedSupplier/" + supplierId)
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(3)
    public void updateAwarded() {
        long awardedId = 5;
        String updatedAwarded = """
                {
                    "id": 5,
                    "tender_src_id": 831317,
                    "date": "2025-01-20",
                    "valueForOne": 100.5,
                    "valueForTwo": 200,
                    "valueForThree": 300,
                    "suppliersId": 166660,
                    "count": "5",
                    "offersCount": 10,
                    "value": "500"
                }
                """;

        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(updatedAwarded)
                .put("/tenders/awarded/update/" + awardedId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void getAwardedById() {
        long awardedId = 55;

        RestAssured
                .get("/tenders/Awarded/" + awardedId)
                .then()
                .statusCode(200)
                .body("id", is((int) awardedId));
    }
}
