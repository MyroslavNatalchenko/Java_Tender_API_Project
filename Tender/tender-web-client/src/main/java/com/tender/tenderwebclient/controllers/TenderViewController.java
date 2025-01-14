package com.tender.tenderwebclient.controllers;

//import com.tender.tenderwebclient.models.TenderObj;
import com.tender.tenderwebapi.model.*;
import com.tender.tenderwebclient.services.TenderViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TenderViewController {
    private TenderViewService service;

    @Autowired
    public TenderViewController(TenderViewService service) {
        this.service = service;
    }

    @GetMapping("")
    public String redirect(){
        return "redirect:/allTenders";
    }

    @GetMapping("/allTenders")
    public String displayAllTenders(Model model){
        List<TenderObj> tenders = service.getAllTenders();
        model.addAttribute("tenders",tenders);
        return "viewAllTenders";
    }

    // Update Tender
    @GetMapping("/updateTender")
    public String displayUpdateSchool(@RequestParam("id") long id, Model model) {
        TenderObj tender = this.service.getTenderById(id);
        model.addAttribute("tender", tender);
        return "tender/updateForm";
    }
    @PostMapping("/updateTender")
    public String submitForm(@ModelAttribute TenderObj tender) {
        this.service.editTender((long) tender.sourceId(),tender);
        return "redirect:/allTenders";
    }

    //Add Tender
    @GetMapping("/addTender")
    public String displayAddSchool(Model model) {
        model.addAttribute("TakenIDs", this.service.getAllTenderID());
        model.addAttribute("tender", new TenderObj(0,0,null,null,null,null,null,null,null));
        return "tender/addForm";
    }
    @PostMapping("/addTender")
    public String addForm(@ModelAttribute TenderObj tender) {
        this.service.addTender(tender);
        return "redirect:/allTenders";
    }

    //Remove Tender
    @GetMapping("/removeTender")
    public String displayDeleteSchool(@RequestParam("id") long id, Model model) {
        TenderObj tender = this.service.getTenderById(id);
        model.addAttribute("tender", tender);
        return "tender/deleteForm";
    }
    @PostMapping("/removeTender")
    public String submitDeleteForm(@ModelAttribute TenderObj tenderObj) {
        this.service.deleteTender(tenderObj.sourceId());
        return "redirect:/allTenders";
    }

    @GetMapping("/TenderDetails")
    public String displayTenderDetails(@RequestParam("id") long id, Model model){
        TenderObj tender = this.service.getTenderById(id);
        PurchaserObj purchaserObj = this.service.getPurchaserByTenderId(id);
        List<AwardedObj> awardeds = this.service.getAwardedByTenderId(id);
        TypeObj type = this.service.getTypeByTenderId(id);
        model.addAttribute("tender", tender);
        model.addAttribute("purchaser", purchaserObj);
        model.addAttribute("awardeds", awardeds);
        model.addAttribute("type", type);
        model.addAttribute("id", id);
        return "tender/viewTenderDetails";
    }

    @GetMapping("/allPurchaser")
    public String displayAllPurchaser(Model model){
        List<PurchaserObj> purchasers = service.getAllPurchasers();
        model.addAttribute("purchasers",purchasers);
        return "viewAllPurchasers";
    }


    @GetMapping("/updatePurchaser")
    public String displayUpdatePurchaser(@RequestParam("id") long id, Model model) {
        PurchaserObj purchaser = this.service.getPurchaserByTenderId(id);
        model.addAttribute("TakenIDs", this.service.getAllPurchaserID());
        model.addAttribute("purchaser", purchaser);
        return "purchaser/updateForm";
    }
    @PostMapping("/updatePurchaser")
    public String submitFormPurchaser(@ModelAttribute PurchaserObj purchaser) {
        this.service.editPurchaser(purchaser.tender_src_id(),purchaser);
        return "redirect:/TenderDetails?id=" + purchaser.tender_src_id();
    }

    @GetMapping("/updateType")
    public String displayUpdateType(@RequestParam("id") long id, Model model) {
        TypeObj type = this.service.getTypeByTenderId(id);
        model.addAttribute("type", type);
        return "type/updateForm";
    }
    @PostMapping("/updateType")
    public String submitFormType(@ModelAttribute TypeObj type) {
        this.service.editType(type.tender_src_id(),type);
        return "redirect:/TenderDetails?id=" + type.tender_src_id();
    }

    @GetMapping("/updateAwarded")
    public String displayUpdateAwarded(@RequestParam("id") long id, Model model) {
        AwardedObj awarded = this.service.getAwardedById(id);
        List<Long> SuppliersID = this.service.getAllSuplliersID();
        model.addAttribute("SuppliersID", SuppliersID);
        model.addAttribute("awarded", awarded);
        return "awarded/updateForm";
    }
    @PostMapping("/updateAwarded")
    public String submitFormAwarded(@ModelAttribute AwardedObj awarded) {
        this.service.editAwarded(awarded.id(),awarded);
        return "redirect:/TenderDetails?id=" + awarded.tender_src_id();
    }

    @GetMapping("/allSuppliers")
    public String displayAllSuppliers(Model model){
        List<SupplierObj> suppliers = service.getAllSuppliers();
        model.addAttribute("suppliers",suppliers);
        return "viewAllSuppliers";
    }

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

    @GetMapping("/updateSupplier")
    public String displayUpdateSupplier(@RequestParam("id") long id, Model model) {
        List<SupplierObj> suppliers = this.service.getAllSuppliers();
        SupplierObj res = new SupplierObj(0,0,null,null);
        for (SupplierObj supplier: suppliers){
            if (supplier.source_id()==id)
                res = supplier;
        }
        model.addAttribute("supplier", res);
        return "supplier/updateForm";
    }
    @PostMapping("/updateSupplier")
    public String submitFormSupplier(@ModelAttribute SupplierObj supplier) {
        this.service.editSupplier(supplier.source_id(),supplier);
        return "redirect:/allSuppliers";
    }
}
