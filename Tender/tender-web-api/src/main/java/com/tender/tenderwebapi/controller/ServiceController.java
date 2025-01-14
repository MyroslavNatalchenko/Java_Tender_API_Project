package com.tender.tenderwebapi.controller;

import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ServiceController {
    private TenderService service;

    @Autowired
    public ServiceController(TenderService service) {
        this.service = service;
    }

    @GetMapping("tenders/SupplierID")
    public ResponseEntity<List<Long>> getIDsSupplier(){
        return new ResponseEntity<>(this.service.getSuppliersID(), HttpStatus.OK);
    }
    @GetMapping("tenders/TenderID")
    public ResponseEntity<List<Integer>> getIDsTender(){
        return new ResponseEntity<>(this.service.getTendersID(),HttpStatus.OK);
    }
    @GetMapping("tenders/PurchaserID")
    public ResponseEntity<List<Integer>> getIDsPurchaser(){
        return new ResponseEntity<>(this.service.getPurchasersID(),HttpStatus.OK);
    }
}
