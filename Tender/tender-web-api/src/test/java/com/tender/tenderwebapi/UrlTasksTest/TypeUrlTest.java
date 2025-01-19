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
public class TypeUrlTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void getTypeByTenderId() {
        long tenderId = 831109;

        RestAssured
                .get("/tenders/TypeTender/" + tenderId)
                .then()
                .statusCode(200)
                .body("tender_src_id", is((int) tenderId));
    }

    @Test
    @Order(2)
    public void updateType() {
        long typeId = 831109;
        String updatedType = """
            {
                "id": 5001,
                "tender_src_id": 831109,
                "sourceId": "TYPE5001",
                "name": "Updated Type",
                "slug": "updated-type"
            }
            """;

        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(updatedType)
                .put("/tenders/type/update/" + typeId)
                .then()
                .statusCode(200);
    }
}
