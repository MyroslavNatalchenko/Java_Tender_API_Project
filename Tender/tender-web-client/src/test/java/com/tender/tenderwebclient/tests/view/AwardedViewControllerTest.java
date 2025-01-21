package com.tender.tenderwebclient.tests.view;

import com.tender.tenderwebapi.model.AwardedObj;
import com.tender.tenderwebclient.controllers.AwardedViewController;
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

@WebMvcTest(AwardedViewController.class)
class AwardedViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TenderViewService service;

    private AwardedObj sampleAwarded;

    @BeforeEach
    void setup() {
        sampleAwarded = new AwardedObj(1, 1001, "2023-01-01", 100.0, 200.0, 300.0, 501, "10", 5, "1000.0");
    }

    @Test
    void testDisplayUpdateAwarded() throws Exception {
        when(service.getAwardedById(1)).thenReturn(sampleAwarded);
        when(service.getAllSuplliersID()).thenReturn(List.of(5001L, 5002L));

        mockMvc.perform(get("/updateAwarded").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("awarded/updateForm"))
                .andExpect(model().attributeExists("awarded", "SuppliersID"));
    }

    @Test
    void testDisplayUpdateAwardedError() throws Exception {
        when(service.getAwardedById(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/updateAwarded").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Awarded with such ID to Update"));
    }
}
