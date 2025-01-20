package com.tender.tenderwebclient.tests;

import com.tender.tenderwebapi.model.*;
import com.tender.tenderwebclient.services.TenderViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;

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

    @Test
    void getTenderById(){
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
}
