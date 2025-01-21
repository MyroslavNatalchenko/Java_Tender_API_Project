package com.tender.tenderwebclient.tests.view;

import com.tender.tenderwebapi.model.TypeObj;
import com.tender.tenderwebclient.controllers.TypeViewController;
import com.tender.tenderwebclient.services.TenderViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TypeViewController.class)
class TypeViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TenderViewService service;

    private TypeObj sampleType;

    @BeforeEach
    void setup() {
        sampleType = new TypeObj(1, 1001, "5001", "Sample Type", "sample-type");
    }

    @Test
    void testDisplayUpdateType() throws Exception {
        when(service.getTypeByTenderId(1)).thenReturn(sampleType);

        mockMvc.perform(get("/updateType").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("type/updateForm"))
                .andExpect(model().attributeExists("type"));
    }

    @Test
    void testDisplayUpdateTypeError() throws Exception {
        when(service.getTypeByTenderId(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/updateType").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Type with such ID to Update"));
    }
}
