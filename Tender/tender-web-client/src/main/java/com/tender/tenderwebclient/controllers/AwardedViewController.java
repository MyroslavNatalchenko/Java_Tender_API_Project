package com.tender.tenderwebclient.controllers;

import com.tender.tenderwebapi.model.AwardedObj;
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
public class AwardedViewController {
    private TenderViewService service;

    @Autowired
    public AwardedViewController(TenderViewService service) {
        this.service = service;
    }

    @GetMapping("/updateAwarded")
    public String displayUpdateAwarded(@RequestParam("id") long id, Model model) {
        try {
            AwardedObj awarded = this.service.getAwardedById(id);
            List<Long> SuppliersID = this.service.getAllSuplliersID();
            model.addAttribute("SuppliersID", SuppliersID);
            model.addAttribute("awarded", awarded);
            return "awarded/updateForm";
        } catch (RuntimeException e){
            model.addAttribute("errorMessage", "No Awarded with such ID to Update");
            return "errorPage";
        }
    }
    @PostMapping("/updateAwarded")
    public String submitFormAwarded(@ModelAttribute AwardedObj awarded) {
        this.service.editAwarded(awarded.id(),awarded);
        return "redirect:/TenderDetails?id=" + awarded.tender_src_id();
    }
}
