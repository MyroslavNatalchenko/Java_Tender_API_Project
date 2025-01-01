package com.tender.tenderwebapi.controller;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderwebapi.model.TenderObj;
import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RestController {
    private TenderService service;

    @Autowired
    public RestController(TenderService service) {
        this.service = service;
    }

    @GetMapping("tenders/allTenders")
    public ResponseEntity<TenderObj> getAllTendersObj(){
        return new ResponseEntity<>(this.service.getAllTenders(), HttpStatus.OK);
    }
}
