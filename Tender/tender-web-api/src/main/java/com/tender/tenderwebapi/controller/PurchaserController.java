package com.tender.tenderwebapi.controller;

import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PurchaserController {
    private TenderService service;

    @Autowired
    public PurchaserController(TenderService service) {
        this.service = service;
    }

    @GetMapping("tenders/allPurchaser")
    public ResponseEntity<List<PurchaserObj>> getAllPurchaserObj(){
        return new ResponseEntity<>(this.service.getAllPurchasers(), HttpStatus.OK);
    }

    @GetMapping("tenders/PurchaserTender/{id}")
    public ResponseEntity<PurchaserObj> getPurchaserByTenderId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getPurchaserByTenderId(id),HttpStatus.OK);
    }

    @PutMapping("tenders/purchaser/update/{id}")
    public ResponseEntity<Object> editPurchaser(@PathVariable long id, @RequestBody PurchaserObj purchaserObj){
        this.service.updatePurchaserById(id, purchaserObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
