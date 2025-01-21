package com.tender.tenderwebclient.tests.view;

import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebclient.controllers.PurchaserViewController;
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

@WebMvcTest(PurchaserViewController.class)
class PurchaserViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TenderViewService service;

    private PurchaserObj samplePurchaser;

    @BeforeEach
    void setup() {
        samplePurchaser = new PurchaserObj(1, 1001, 5001, "123", "Sample Purchaser");
    }

    @Test
    void testDisplayAllPurchasers() throws Exception {
        when(service.getAllPurchasers()).thenReturn(List.of(samplePurchaser));

        mockMvc.perform(get("/allPurchaser"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllPurchasers"))
                .andExpect(model().attributeExists("purchasers"));
    }

    @Test
    void testDisplayAllPurchasersError() throws Exception {
        when(service.getAllPurchasers()).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/allPurchaser"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Purchasers"));
    }

    @Test
    void testDisplayUpdatePurchaser() throws Exception {
        when(service.getPurchaserByTenderId(1)).thenReturn(samplePurchaser);
        when(service.getAllPurchaserID()).thenReturn(List.of(1001, 1002));

        mockMvc.perform(get("/updatePurchaser").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("purchaser/updateForm"))
                .andExpect(model().attributeExists("purchaser", "TakenIDs"));
    }

    @Test
    void testDisplayUpdatePurchaserError() throws Exception {
        when(service.getPurchaserByTenderId(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/updatePurchaser").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Purchaser with such ID to Update"));
    }
}
