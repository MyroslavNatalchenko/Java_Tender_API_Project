package com.tender.tenderwebapi.controller;

import com.tender.tenderwebapi.model.*;
import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TenderController {
    private TenderService service;
    @Autowired
    public TenderController(TenderService service) {
        this.service = service;
    }

    // TODO Разделить на несколько Controller

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
    public ResponseEntity<Object> editTender(@PathVariable long id, @RequestBody TenderObj tenderObj) {
        this.service.updateTenderById(id, tenderObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
