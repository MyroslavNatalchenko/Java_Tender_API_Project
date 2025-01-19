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
public class SupplierUrlTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @Order(1)
    public void getAllSuppliers() {
        RestAssured
                .get("/tenders/allSuppliers")
                .then()
                .statusCode(200)
                .body("$.size()", greaterThanOrEqualTo(0));
    }

    @Test
    @Order(2)
    public void addSupplier() {
        String newSupplier = """
            {
                "id": 1,
                "source_id": 3001,
                "name": "Test Supplier",
                "slug": "test-supplier"
            }
            """;

        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(newSupplier)
                .post("/tenders/supplier/add")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(3)
    public void updateSupplier() {
        long supplierId = 3001;
        String updatedSupplier = """
            {
                "id": 1,
                "source_id": 3001,
                "name": "Updated Supplier",
                "slug": "updated-supplier"
            }
            """;

        RestAssured
                .with()
                .contentType(ContentType.JSON)
                .body(updatedSupplier)
                .put("/tenders/supplier/update/" + supplierId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(4)
    public void deleteSupplier() {
        long supplierId = 3001;

        RestAssured
                .delete("/tenders/supplier/delete/" + supplierId)
                .then()
                .statusCode(200);
    }

    @Test
    @Order(5)
    public void getAllSuppliersAfterDeletion() {
        RestAssured
                .get("/tenders/allSuppliers")
                .then()
                .statusCode(200)
                .body("find { it.source_id == 3001 }", nullValue());
    }
}
