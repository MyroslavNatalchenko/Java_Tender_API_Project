package com.tender.tenderwebclient.controllers;

import com.tender.tenderwebapi.model.PurchaserObj;
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
public class PurchaserViewController {
    private TenderViewService service;

    @Autowired
    public PurchaserViewController(TenderViewService service) {
        this.service = service;
    }

    //All Purchasers view
    @GetMapping("/allPurchaser")
    public String displayAllPurchaser(Model model){
        List<PurchaserObj> purchasers = service.getAllPurchasers();
        model.addAttribute("purchasers",purchasers);
        return "viewAllPurchasers";
    }


    //Update Purchaser
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
}
