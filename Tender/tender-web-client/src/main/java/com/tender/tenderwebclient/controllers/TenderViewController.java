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
    // TODO Допиши все возможные случаи получения ошибки
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
    public String displayAllTenders(Model model) {
        try {
            List<TenderObj> tenders = service.getAllTenders();
            model.addAttribute("tenders", tenders);
            return "viewAllTenders";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Error");
            return "errorPage";
        }
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
        return "redirect:/TenderDetails?id=" + tender.sourceId();
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
        List<Integer> ids = this.service.getAllTenderID();
        TenderObj tender = this.service.getTenderById(id);
        model.addAttribute("IDs", ids);
        model.addAttribute("tender", tender);
        return "tender/deleteForm";
    }
    @PostMapping("/removeTender")
    public String submitDeleteForm(@ModelAttribute TenderObj tenderObj) {
        this.service.deleteTender(tenderObj.sourceId());
        return "redirect:/allTenders";
    }

    //Tender details
    @GetMapping("/TenderDetails")
    public String displayTenderDetails(@RequestParam("id") long id, Model model){
        try {
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
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "Error");
            return "errorPage";
        }
    }
}
