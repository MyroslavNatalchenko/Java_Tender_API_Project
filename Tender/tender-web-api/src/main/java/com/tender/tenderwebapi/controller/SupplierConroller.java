package com.tender.tenderwebapi.controller;

import com.tender.tenderwebapi.model.SupplierObj;
import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class SupplierConroller {
    private TenderService service;

    @Autowired
    public SupplierConroller(TenderService service) {
        this.service = service;
    }

    @GetMapping("tenders/allSuppliers")
    public ResponseEntity<List<SupplierObj>> getAllSuppliers(){
        return new ResponseEntity<>(this.service.getAllSuppliers(), HttpStatus.OK);
    }

    @PostMapping("tenders/supplier/add")
    public ResponseEntity<Object> addSupplier(@RequestBody SupplierObj supplierObj){
        this.service.addSupplier(supplierObj);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("tenders/supplier/update/{id}")
    public ResponseEntity<Object> editSupplier(@PathVariable long id, @RequestBody SupplierObj supplierObj){
        this.service.updateSupplier(id, supplierObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("tenders/supplier/delete/{id}")
    public ResponseEntity<Object> deleteTender(@PathVariable long id) {
        this.service.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
