package com.tender.tenderwebclient.controllers;

import com.tender.tenderwebapi.model.SupplierObj;
import com.tender.tenderwebapi.model.TenderObj;
import com.tender.tenderwebclient.services.TenderViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SupplierViewController {
    private TenderViewService service;

    @Autowired
    public SupplierViewController(TenderViewService service) {
        this.service = service;
    }

    //View All Suppliers
    @GetMapping("/allSuppliers")
    public String displayAllSuppliers(Model model){
        List<SupplierObj> suppliers = service.getAllSuppliers();
        model.addAttribute("suppliers",suppliers);
        return "viewAllSuppliers";
    }

    //Add Supplier
    @GetMapping("/addSupplier")
    public String displayAddSupplier(Model model) {
        model.addAttribute("TakenIDs", this.service.getAllSuplliersID());
        model.addAttribute("supplier", new SupplierObj(0,0,null,null));
        return "supplier/addForm";
    }
    @PostMapping("/addSupplier")
    public String addFormSupplier(@ModelAttribute SupplierObj supplier) {
        this.service.addSupplier(supplier);
        return "redirect:/allSuppliers";
    }

    //Update Supplier
    @GetMapping("/updateSupplier")
    public String displayUpdateSupplier(@RequestParam("id") long id, Model model) {
        SupplierObj supplier = this.service.getSupplierById(id);
        model.addAttribute("supplier", supplier);
        return "supplier/updateForm";
    }
    @PostMapping("/updateSupplier")
    public String submitFormSupplier(@ModelAttribute SupplierObj supplier) {
        this.service.editSupplier(supplier.source_id(),supplier);
        return "redirect:/allSuppliers";
    }

    //Remove Supplier
    @GetMapping("/removeSupplier")
    public String displayDeleteSupplier(@RequestParam("id") long id, Model model) {
        int size_awarded = this.service.getAwardedBySupplierId(id).size();
        SupplierObj supplier = this.service.getSupplierById(id);
        model.addAttribute("supplier", supplier);
        model.addAttribute("size", size_awarded);
        return "supplier/deleteForm";
    }
    @PostMapping("/removeSupplier")
    public String submitDeleteForm(@ModelAttribute SupplierObj supplierObj) {
        this.service.deleteSupplier(supplierObj.source_id());
        return "redirect:/allSuppliers";
    }
}
