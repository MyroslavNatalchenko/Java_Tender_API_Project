package com.tender.tenderwebclient.controllers;

import com.tender.tenderwebapi.model.TypeObj;
import com.tender.tenderwebclient.services.TenderViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TypeViewController {
    private TenderViewService service;

    @Autowired
    public TypeViewController(TenderViewService service) {
        this.service = service;
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
}
