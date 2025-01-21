package com.tender.tenderwebclient.tests;

import com.tender.tenderwebapi.model.*;
import com.tender.tenderwebclient.services.TenderViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestClientTest
class TenderViewServiceTest {
    MockServerRestClientCustomizer customizer = new MockServerRestClientCustomizer();
    RestClient.Builder builder = RestClient.builder();
    TenderViewService service;

    @BeforeEach
    void setup(){
        customizer.customize(builder);
        service = new TenderViewService(builder.build(), "http://localhost:8080/tenders");
    }

    // ***********************
    // *** TENDER TESTS ******
    // ***********************

    @Test
    void getTenderAllGood(){
        customizer.getServer()
                .expect(MockRestRequestMatchers
                        .requestTo("http://localhost:8080/tenders/allTenders"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                                [
                                    {
                                      "id": 5,
                                      "sourceId": 831317,
                                      "date": "2021-05-26",
                                      "deadlineDate": "2024-07-24",
                                      "deadlineLengthDays": "1155",
                                      "title": "Updated Title",
                                      "category": "Updated Category",
                                      "sid": "7611066",
                                      "sourceUrl": "https://contrataciondelestado.es/wps/poc?uri=deeplink:detalle_licitacion&idEvl=BnqMzoYrx%2Fh7h85%2Fpmmsfw%3D%3D"
                                    },
                                    {
                                      "id": 6,
                                      "sourceId": 831316,
                                      "date": "2021-05-24",
                                      "deadlineDate": "2024-07-21",
                                      "deadlineLengthDays": "1153",
                                      "title": "New Title",
                                      "category": "New Category",
                                      "sid": "831316",
                                      "sourceUrl": "https://contrataciondelestado.es/wps/poc?uri=deeplink:detalle_licitacion&idEvl=BnqMzoYrx%2Fh7h85%2Fpmmsfw%3D%3D"
                                    }
                                ]
                                """
                        , MediaType.APPLICATION_JSON));
        List<TenderObj> tenderObj = service.getAllTenders();

        assertEquals(tenderObj.size(),2);
        assertEquals("7611066", tenderObj.get(0).sid());
        assertEquals("831316", tenderObj.get(1).sid());
    }

    @Test
    void getTenderAllFale() {
        customizer.getServer()
                .expect(MockRestRequestMatchers
                        .requestTo("http://localhost:8080/tenders/allTenders"))
                .andRespond(MockRestResponseCreators.withBadRequest());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.getAllTenders();
        });

        assertNotNull(exception);
        assertEquals("400 Bad Request: [no body]", exception.getMessage());
    }

    @Test
    void getTenderByIdGood(){
        customizer.getServer()
                .expect(MockRestRequestMatchers
                        .requestTo("http://localhost:8080/tenders/831317"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                                {
                                  "id": 5,
                                  "sourceId": 831317,
                                  "date": "2021-05-26",
                                  "deadlineDate": "2024-07-24",
                                  "deadlineLengthDays": "1155",
                                  "title": "Updated Title",
                                  "category": "Updated Category",
                                  "sid": "7611066",
                                  "sourceUrl": "https://contrataciondelestado.es/wps/poc?uri=deeplink:detalle_licitacion&idEvl=BnqMzoYrx%2Fh7h85%2Fpmmsfw%3D%3D"
                                }
                                """
                , MediaType.APPLICATION_JSON));
        TenderObj tenderObj = service.getTenderById(831317);
        assertEquals("7611066", tenderObj.sid());
    }

    @Test
    void getTenderByIdFail() {
        customizer.getServer()
                .expect(MockRestRequestMatchers
                        .requestTo("http://localhost:8080/tenders/111"))
                .andRespond(MockRestResponseCreators.withBadRequest());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.getTenderById(111);
        });

        assertNotNull(exception);
        assertEquals("400 Bad Request: [no body]", exception.getMessage());
    }

    @Test
    void addTenderSuccess() {
        TenderObj tenderObj = new TenderObj(10, 12345, "2023-01-01", "2024-01-01", "365", "Test Title", "Test Category", "123", "http://test.com");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/add"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.addTender(tenderObj));
    }

    @Test
    void editTenderSuccess() {
        TenderObj tenderObj = new TenderObj(10, 12345, "2023-01-01", "2024-01-01", "365", "Updated Title", "Updated Category", "123", "http://test.com");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/update/10"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.PUT))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.editTender(10, tenderObj));
    }

    @Test
    void deleteTenderSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/delete/10"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.DELETE))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.deleteTender(10));
    }

    // **************************
    // *** PURCHASER TESTS ******
    // **************************

    @Test
    void getAllPurchasersSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/allPurchaser"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        [
                            {"id":1,"tender_src_id":123,"sourceId":456,"sid":"123","name":"Purchaser One"},
                            {"id":2,"tender_src_id":124,"sourceId":457,"sid":"124","name":"Purchaser Two"}
                        ]
                        """, MediaType.APPLICATION_JSON));

        List<PurchaserObj> purchasers = service.getAllPurchasers();
        assertEquals(2, purchasers.size());
        assertEquals("Purchaser One", purchasers.get(0).name());
        assertEquals("Purchaser Two", purchasers.get(1).name());
    }

    @Test
    void getPurchaserByTenderIdSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/PurchaserTender/123"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        {"id":1,"tender_src_id":123,"sourceId":456,"sid":"123","name":"Purchaser One"}
                        """, MediaType.APPLICATION_JSON));

        PurchaserObj purchaser = service.getPurchaserByTenderId(123);
        assertEquals("Purchaser One", purchaser.name());
        assertEquals(123, purchaser.tender_src_id());
    }

    @Test
    void editPurchaserSuccess() {
        PurchaserObj purchaserObj = new PurchaserObj(1, 12345, 678, "12345", "Updated Purchaser Name");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/purchaser/update/1"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.PUT))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.editPurchaser(1, purchaserObj));
    }

    @Test
    void editPurchaserFail() {
        PurchaserObj purchaserObj = new PurchaserObj(1, 12345, 678, "12345", "Updated Purchaser Name");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/purchaser/update/1"))
                .andRespond(MockRestResponseCreators.withBadRequest());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.editPurchaser(1, purchaserObj));
        assertNotNull(exception);
    }

    // ************************
    // *** AWARDED TESTS ******
    // ************************

    @Test
    void getAwardedByTenderIdSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/AwardedTender/123"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        [
                            {"id":1,"tender_src_id":123,"date":"2023-01-01","valueForOne":100.0,"valueForTwo":200.0,"valueForThree":300.0,"suppliersId":1,"count":"10","offersCount":5,"value":"1000.0"},
                            {"id":2,"tender_src_id":123,"date":"2023-02-01","valueForOne":150.0,"valueForTwo":250.0,"valueForThree":350.0,"suppliersId":2,"count":"20","offersCount":10,"value":"2000.0"}
                        ]
                        """, MediaType.APPLICATION_JSON));

        List<AwardedObj> awardedList = service.getAwardedByTenderId(123);
        assertEquals(2, awardedList.size());
        assertEquals(100.0, awardedList.get(0).valueForOne());
        assertEquals(150.0, awardedList.get(1).valueForOne());
    }

    @Test
    void getAwardedByIdSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/Awarded/1"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        {"id":1,"tender_src_id":123,"date":"2023-01-01","valueForOne":100.0,"valueForTwo":200.0,"valueForThree":300.0,"suppliersId":1,"count":"10","offersCount":5,"value":"1000.0"}
                        """, MediaType.APPLICATION_JSON));

        AwardedObj awarded = service.getAwardedById(1);
        assertEquals(100.0, awarded.valueForOne());
        assertEquals("1000.0", awarded.value());
    }


    @Test
    void getAwardedBySupplierIdSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/AwardedSupplier/123"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        [
                            {"id":1,"tender_src_id":123,"date":"2023-01-01","valueForOne":100.0,"valueForTwo":200.0,"valueForThree":300.0,"suppliersId":123,"count":"10","offersCount":5,"value":"1000.0"},
                            {"id":2,"tender_src_id":124,"date":"2023-02-01","valueForOne":150.0,"valueForTwo":250.0,"valueForThree":350.0,"suppliersId":123,"count":"20","offersCount":10,"value":"2000.0"}
                        ]
                        """, MediaType.APPLICATION_JSON));

        List<AwardedObj> awardedList = service.getAwardedBySupplierId(123);
        assertEquals(2, awardedList.size());
        assertEquals(100.0, awardedList.get(0).valueForOne());
        assertEquals(150.0, awardedList.get(1).valueForOne());
    }

    @Test
    void editAwardedSuccess() {
        AwardedObj awardedObj = new AwardedObj(1, 123, "2023-01-01", 100.0, 200.0, 300.0, 123, "10", 5, "1000.0");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/awarded/update/1"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.PUT))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.editAwarded(1, awardedObj));
    }

    @Test
    void getAwardedBySupplierIdFail() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/AwardedSupplier/123"))
                .andRespond(MockRestResponseCreators.withServerError());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.getAwardedBySupplierId(123));
        assertNotNull(exception);
    }

    // *************************
    // *** SUPPLIER TESTS ******
    // *************************

    @Test
    void addSupplierSuccess() {
        SupplierObj supplierObj = new SupplierObj(1, 123, "Supplier Name", "supplier-name");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/supplier/add"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.addSupplier(supplierObj));
    }

    @Test
    void deleteSupplierSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/supplier/delete/1"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.DELETE))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.deleteSupplier(1));
    }

    @Test
    void editSupplierSuccess() {
        SupplierObj supplierObj = new SupplierObj(1, 123, "Updated Supplier Name", "updated-supplier-name");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/supplier/update/1"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.PUT))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.editSupplier(1, supplierObj));
    }

    @Test
    void getAllSuppliersSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/allSuppliers"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        [
                            {"id":1,"source_id":123,"name":"Supplier One","slug":"supplier-one"},
                            {"id":2,"source_id":124,"name":"Supplier Two","slug":"supplier-two"}
                        ]
                        """, MediaType.APPLICATION_JSON));

        List<SupplierObj> suppliers = service.getAllSuppliers();
        assertEquals(2, suppliers.size());
        assertEquals("Supplier One", suppliers.get(0).name());
        assertEquals("Supplier Two", suppliers.get(1).name());
    }

    @Test
    void getSupplierByIdSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/supplier/1"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        {"id":1,"source_id":123,"name":"Supplier One","slug":"supplier-one"}
                        """, MediaType.APPLICATION_JSON));

        SupplierObj supplier = service.getSupplierById(1);

        assertNotNull(supplier);
        assertEquals(1, supplier.id());
        assertEquals(123, supplier.source_id());
        assertEquals("Supplier One", supplier.name());
        assertEquals("supplier-one", supplier.slug());
    }


    @Test
    void getSupplierByIdNotFound() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/supplier/1"))
                .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.getSupplierById(1);
        });

        assertNotNull(exception);
    }

    // *********************
    // *** TYPE TESTS ******
    // *********************

    @Test
    void getTypeByTenderIdSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/TypeTender/123"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        {"id":1,"tender_src_id":123,"sourceId":"12345","name":"Type Name","slug":"type-name"}
                        """, MediaType.APPLICATION_JSON));

        TypeObj typeObj = service.getTypeByTenderId(123);
        assertEquals("Type Name", typeObj.name());
        assertEquals("12345", typeObj.sourceId());
    }

    @Test
    void editTypeSuccess() {
        TypeObj typeObj = new TypeObj(1, 123, "12345", "Updated Type Name", "updated-type-name");
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/type/update/1"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.PUT))
                .andRespond(MockRestResponseCreators.withSuccess());

        assertDoesNotThrow(() -> service.editType(1, typeObj));
    }


    // *******************
    // *** ID TESTS ******
    // *******************

    @Test
    void getAllSuppliersIDSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/SupplierID"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        [1, 2, 3, 4]
                        """, MediaType.APPLICATION_JSON));

        List<Long> supplierIds = service.getAllSuplliersID();
        assertEquals(4, supplierIds.size());
        assertEquals(1, supplierIds.get(0));
        assertEquals(4, supplierIds.get(3));
    }

    @Test
    void getAllTenderIDSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/TenderID"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        [101, 102, 103]
                        """, MediaType.APPLICATION_JSON));

        List<Integer> tenderIds = service.getAllTenderID();
        assertEquals(3, tenderIds.size());
        assertEquals(101, tenderIds.get(0));
        assertEquals(103, tenderIds.get(2));
    }

    @Test
    void getAllPurchaserIDSuccess() {
        customizer.getServer()
                .expect(MockRestRequestMatchers.requestTo("http://localhost:8080/tenders/PurchaserID"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        """
                        [201, 202, 203, 204]
                        """, MediaType.APPLICATION_JSON));

        List<Integer> purchaserIds = service.getAllPurchaserID();
        assertEquals(4, purchaserIds.size());
        assertEquals(201, purchaserIds.get(0));
        assertEquals(204, purchaserIds.get(3));
    }

}
