package com.tender.tenderwebapi.UrlTasksTest;

import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.basePath;
import static org.hamcrest.Matchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //używamy
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
        RestAssured.get(basePath + "/tenders/allTenders")
                .then()
                .statusCode(200);
               // .body("$.size()",is(100)); //dollar - root element
    }
}
