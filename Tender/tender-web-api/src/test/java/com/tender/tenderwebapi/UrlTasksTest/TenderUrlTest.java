package com.tender.tenderwebapi.UrlTasksTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TenderUrlTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void getAllTenders() {
        RestAssured
                .get("/tenders/allTenders")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(2)
    public void addTender() {
        String newTender = """
            {
                "id": 1,
                "sourceId": 2001,
                "date": "2025-01-15",
                "deadlineDate": "2025-01-20",
                "deadlineLengthDays": "5",
                "title": "Test Tender",
                "category": "IT Services",
                "sid": "TND2001",
                "sourceUrl": "http://example.com"
            }
            """;

        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(newTender)
                .post("/tenders/add")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(3)
    public void getTenderById() {
        long tenderId = 2001;

        RestAssured
                .get("/tenders/" + tenderId)
                .then()
                .statusCode(200)
                .body("sourceId", is(2001))
                .body("title", is("Test Tender"));
    }

    @Test
    @Order(4)
    public void updateTender() {
        long tenderId = 2001;
        String updatedTender = """
            {
                "id": 1,
                "sourceId": 2001,
                "date": "2025-01-15",
                "deadlineDate": "2025-01-25",
                "deadlineLengthDays": "10",
                "title": "Updated Test Tender",
                "category": "Construction",
                "sid": "TND2001",
                "sourceUrl": "http://updatedexample.com"
            }
            """;

        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(updatedTender)
                .put("/tenders/update/" + tenderId)
                .then()
                .statusCode(200);
    }


    @Test
    @Order(5)
    public void deleteTender() {
        long tenderId = 2001;

        RestAssured
                .delete("/tenders/delete/" + tenderId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(6)
    public void getTenderByNonExistentId() {
        long tenderId = 9999;

        RestAssured
                .get("/tenders/" + tenderId)
                .then()
                .statusCode(404)
                .contentType("text/plain")
                .body(equalTo("No Tender with such ID"));
    }
}
