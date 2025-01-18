package com.tender.tenderwebapi.controller;

import com.tender.tenderwebapi.model.AwardedObj;
import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class AwardedController {
    private TenderService service;

    @Autowired
    public AwardedController(TenderService service) {
        this.service = service;
    }

    @GetMapping("tenders/AwardedTender/{id}")
    public ResponseEntity<List<AwardedObj>> getAwardedByTenderId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getAwardedByTenderId(id), HttpStatus.OK);
    }

    @GetMapping("tenders/AwardedSupplier/{id}")
    public ResponseEntity<List<AwardedObj>> getAwardedBySupplierId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getAwardedBySupplier(id), HttpStatus.OK);
    }

    @PutMapping("tenders/awarded/update/{id}")
    public ResponseEntity<Object> editAwarded(@PathVariable long id, @RequestBody AwardedObj awardedObj){
        this.service.updateAwardedById(id, awardedObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("tenders/Awarded/{id}")
    public ResponseEntity<AwardedObj> getAwardedById(@PathVariable long id){
        return new ResponseEntity<>(this.service.getAwardedbyBdId(id),HttpStatus.OK);
    }
}
