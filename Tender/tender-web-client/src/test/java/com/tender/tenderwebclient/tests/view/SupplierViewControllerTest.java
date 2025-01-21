package com.tender.tenderwebclient.tests.view;

import com.tender.tenderwebapi.model.SupplierObj;
import com.tender.tenderwebclient.controllers.SupplierViewController;
import com.tender.tenderwebclient.services.TenderViewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SupplierViewController.class)
class SupplierViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TenderViewService service;

    private SupplierObj sampleSupplier;

    @BeforeEach
    void setup() {
        sampleSupplier = new SupplierObj(1, 123, "Sample Supplier", "sample-supplier");
    }

    // *********
    // ALL SUPPLIERS TESTS
    // *********
    @Test
    void testDisplayAllSuppliers() throws Exception {
        when(service.getAllSuppliers()).thenReturn(List.of(sampleSupplier));

        mockMvc.perform(get("/allSuppliers"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewAllSuppliers"))
                .andExpect(model().attributeExists("suppliers"));
    }

    @Test
    void testDisplayAllSuppliersError() throws Exception {
        when(service.getAllSuppliers()).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/allSuppliers"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Suppliers"));
    }

    // *********
    // ADD SUPPLIER TESTS
    // *********
    @Test
    void testDisplayAddSupplier() throws Exception {
        when(service.getAllSuplliersID()).thenReturn(List.of(123L, 124L));

        mockMvc.perform(get("/addSupplier"))
                .andExpect(status().isOk())
                .andExpect(view().name("supplier/addForm"))
                .andExpect(model().attributeExists("supplier", "TakenIDs"));
    }

    // *********
    // UPDATE SUPPLIER TESTS
    // *********
    @Test
    void testDisplayUpdateSupplier() throws Exception {
        when(service.getSupplierById(1)).thenReturn(sampleSupplier);

        mockMvc.perform(get("/updateSupplier").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("supplier/updateForm"))
                .andExpect(model().attributeExists("supplier"));
    }

    @Test
    void testDisplayUpdateSupplierError() throws Exception {
        when(service.getSupplierById(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/updateSupplier").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Supplier with such ID to Update"));
    }
    // *********
    // REMOVE SUPPLIER TESTS
    // *********
    @Test
    void testDisplayDeleteSupplier() throws Exception {
        when(service.getAwardedBySupplierId(1)).thenReturn(List.of());
        when(service.getSupplierById(1)).thenReturn(sampleSupplier);

        mockMvc.perform(get("/removeSupplier").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("supplier/deleteForm"))
                .andExpect(model().attributeExists("supplier", "size"));
    }

    @Test
    void testDisplayDeleteSupplierError() throws Exception {
        when(service.getSupplierById(1)).thenThrow(RuntimeException.class);

        mockMvc.perform(get("/removeSupplier").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("errorPage"))
                .andExpect(model().attribute("errorMessage", "No Supplier with such ID to Delete"));
    }
}
