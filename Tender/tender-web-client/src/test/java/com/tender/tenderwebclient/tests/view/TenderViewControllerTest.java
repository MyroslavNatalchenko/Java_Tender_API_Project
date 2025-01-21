package com.tender.tenderwebclient.tests.view;

import com.tender.tenderwebapi.model.*;
import com.tender.tenderwebclient.controllers.TenderViewController;
import com.tender.tenderwebclient.services.TenderViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TenderViewController.class)
class TenderViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TenderViewService service;

    private TenderObj sampleTender;
    private PurchaserObj samplePurchaser;
    private TypeObj sampleType;
    private AwardedObj sampleAwarded;

    @BeforeEach
    void setup() {
        sampleTender = new TenderObj(1, 1001, "2023-01-01", "2024-01-01", "365", "Sample Title", "Sample Category", "123", "http://example.com");
        samplePurchaser = new PurchaserObj(1, 1001, 5001, "123", "Sample Purchaser");
        sampleType = new TypeObj(1, 1001, "5001", "Sample Type", "sample-type");
        sampleAwarded = new AwardedObj(1, 1001, "2023-01-01", 100.0, 200.0, 300.0, 501, "10", 5, "1000.0");
    }

    // *********
    // REDIRECT TEST
    // *********
    @Test
    void testRedirectToAllTenders() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/allTenders"));
    }

    // *********
    // ALL TENDERS TESTS
    // *********
    @Test
    void testDisplayAllTenders() throws Exception {
        when(service.getAllTenders()).thenReturn(List.of(sampleTender));

        mockMvc.perform(get("/allTenders"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllTenders"))
                .andExpect(model().attributeExists("tenders"));
    }

    @Test
    void testDisplayAllTendersError() throws Exception {
        when(service.getAllTenders()).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/allTenders"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Tenders"));
    }

    // *********
    // UPDATE TENDER TESTS
    // *********
    @Test
    void testDisplayUpdateTender() throws Exception {
        when(service.getTenderById(1)).thenReturn(sampleTender);

        mockMvc.perform(get("/updateTender").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tender/updateForm"))
                .andExpect(model().attributeExists("tender"));
    }

    @Test
    void testDisplayUpdateTenderError() throws Exception {
        when(service.getTenderById(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/updateTender").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Tender with Such Id to edit"));
    }

    // *********
    // ADD TENDER TESTS
    // *********
    @Test
    void testDisplayAddTender() throws Exception {
        when(service.getAllTenderID()).thenReturn(List.of(1001, 1002));

        mockMvc.perform(get("/addTender"))
                .andExpect(status().isOk())
                .andExpect(view().name("tender/addForm"))
                .andExpect(model().attributeExists("tender", "TakenIDs"));
    }

    // *********
    // REMOVE TENDER TESTS
    // *********
    @Test
    void testDisplayDeleteTender() throws Exception {
        when(service.getAllTenderID()).thenReturn(List.of(1001, 1002));
        when(service.getTenderById(1)).thenReturn(sampleTender);

        mockMvc.perform(get("/removeTender").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tender/deleteForm"))
                .andExpect(model().attributeExists("tender", "IDs"));
    }

    @Test
    void testDisplayDeleteTenderError() throws Exception {
        when(service.getTenderById(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/removeTender").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Tender with Such Id to Delete"));
    }

    // *********
    // TENDER DETAILS TESTS
    // *********
    @Test
    void testDisplayTenderDetails() throws Exception {
        when(service.getTenderById(1)).thenReturn(sampleTender);
        when(service.getPurchaserByTenderId(1)).thenReturn(samplePurchaser);
        when(service.getAwardedByTenderId(1)).thenReturn(List.of(sampleAwarded));
        when(service.getTypeByTenderId(1)).thenReturn(sampleType);

        mockMvc.perform(get("/TenderDetails").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("tender/viewTenderDetails"))
                .andExpect(model().attributeExists("tender", "purchaser", "awardeds", "type", "id"));
    }

    @Test
    void testDisplayTenderDetailsError() throws Exception {
        when(service.getTenderById(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/TenderDetails").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Tender with Such Id or There is mistake in data"));
    }
}

