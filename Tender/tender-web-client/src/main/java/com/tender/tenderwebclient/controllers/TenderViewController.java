package com.tender.tenderwebclient.controllers;

//import com.tender.tenderwebclient.models.TenderObj;
import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebapi.model.TenderObj;
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
        return "updateForm";
    }
    @PostMapping("/updateTender")
    public String submitForm(@ModelAttribute TenderObj tender) {
        this.service.editTender((long) tender.sourceId(),tender);
        return "redirect:/allTenders";
    }

    @GetMapping("/TenderDetails")
    public String displayTenderDetails(@RequestParam("id") long id, Model model){
        PurchaserObj purchaserObj = service.getPurchaserByTenderId(id);
        model.addAttribute("purchaser", purchaserObj);
        model.addAttribute("id", id);
        return "viewTenderDetails";
    }

    @GetMapping("/allPurchaser")
    public String displayAllPurchaser(Model model){
        List<PurchaserObj> purchasers = service.getAllPurchasers();
        model.addAttribute("purchasers",purchasers);
        return "viewAllPurchasers";
    }
}
