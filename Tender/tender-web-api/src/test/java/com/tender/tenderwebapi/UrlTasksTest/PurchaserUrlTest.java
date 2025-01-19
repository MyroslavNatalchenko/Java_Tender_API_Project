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
public class PurchaserUrlTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void getAllPurchasers() {
        RestAssured
                .get("/tenders/allPurchaser")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(2)
    public void getPurchaserByTenderId() {
        long tenderId = 831109;

        RestAssured
                .get("/tenders/PurchaserTender/" + tenderId)
                .then()
                .statusCode(200)
                .body("tender_src_id", is((int) tenderId));
    }

    @Test
    @Order(3)
    public void updatePurchaser() {
        long purchaserId = 831109;
        String updatedPurchaser = """
            {
                "id": 4001,
                "tender_src_id": 831109,
                "sourceId": 3001,
                "sid": "PRC3001",
                "name": "Updated Purchaser"
            }
            """;

        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(updatedPurchaser)
                .put("/tenders/purchaser/update/" + purchaserId)
                .then()
                .statusCode(200);
    }
}
