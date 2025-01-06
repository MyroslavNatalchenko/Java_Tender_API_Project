package com.tender.tenderwebapi.controller;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderwebapi.model.AwardedObj;
import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebapi.model.TenderObj;
import com.tender.tenderwebapi.model.TypeObj;
import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestController {
    private TenderService service;
    @Autowired
    public RestController(TenderService service) {
        this.service = service;
    }

    /// ***************************************************************
    /// *** TENDERS: find all + byId, add, delete by id, edit by id ***
    /// ***************************************************************
    @GetMapping("tenders/allTenders")
    public ResponseEntity<List<TenderObj>> getAllTendersObj(){
        return new ResponseEntity<>(this.service.getAllTenders(), HttpStatus.OK);
    }

    @GetMapping("tenders/{id}")
    public ResponseEntity<TenderObj> getTenderById(@PathVariable long id){
        return new ResponseEntity<>(this.service.getTenderById(id),HttpStatus.OK);
    }

    @PostMapping("tenders/add")
    public ResponseEntity<Object> addTender(@RequestBody TenderObj tenderObj){
        this.service.addTender(tenderObj);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("tenders/delete/{id}")
    public ResponseEntity<Object> deleteTender(@PathVariable long id) {
        this.service.deleteTenderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("tenders/update/{id}")
    public ResponseEntity<Object> editTender(@PathVariable long id, @RequestBody TenderObj tenderObj){
        this.service.updateTenderById(id, tenderObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /// *****************************************************************
    /// *** PURCHASER: find all + byId, add, delete by id, edit by id ***
    /// *****************************************************************
    @GetMapping("tenders/allPurchaser")
    public ResponseEntity<List<PurchaserObj>> getAllPurchaserObj(){
        return new ResponseEntity<>(this.service.getAllPurchasers(), HttpStatus.OK);
    }

    @GetMapping("tenders/PurchaserTender/{id}")
    public ResponseEntity<PurchaserObj> getPurchaserByTenderId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getPurchaserByTenderId(id),HttpStatus.OK);
    }

    @PostMapping("tenders/purchaser/add")
    public ResponseEntity<Object> addPruchaser(@RequestBody PurchaserObj purchaserObj){
        this.service.addPurchaser(purchaserObj);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("tenders/purchaser/delete/{id}")
    public ResponseEntity<Object> deletePurchaser(@PathVariable long id) {
        this.service.deletePurchaserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("tenders/purchaser/update/{id}")
    public ResponseEntity<Object> editPurchaser(@PathVariable long id, @RequestBody PurchaserObj purchaserObj){
        this.service.updatePurchaserById(id, purchaserObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("tenders/TypeTender/{id}")
    public ResponseEntity<TypeObj> getTypeByTenderId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getTypeByTenderId(id),HttpStatus.OK);
    }

    @GetMapping("tenders/AwardedTender/{id}")
    public ResponseEntity<List<AwardedObj>> getAwardedByTenderId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getAwardedByTenderId(id),HttpStatus.OK);
    }
}
