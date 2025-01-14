package com.tender.tenderwebapi.controller;

import com.tender.tenderwebapi.model.TypeObj;
import com.tender.tenderwebapi.services.TenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class TypeController {
    private TenderService service;

    @Autowired
    public TypeController(TenderService service) {
        this.service = service;
    }

    @GetMapping("tenders/TypeTender/{id}")
    public ResponseEntity<TypeObj> getTypeByTenderId(@PathVariable long id){
        return new ResponseEntity<>(this.service.getTypeByTenderId(id), HttpStatus.OK);
    }

    @PutMapping("tenders/type/update/{id}")
    public ResponseEntity<Object> editType(@PathVariable long id, @RequestBody TypeObj typeObj){
        this.service.updateTypeById(id, typeObj);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
