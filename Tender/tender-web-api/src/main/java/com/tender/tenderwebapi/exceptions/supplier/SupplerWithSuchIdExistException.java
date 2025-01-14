package com.tender.tenderwebapi.exceptions.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class SupplerWithSuchIdExistException extends RuntimeException{
    public SupplerWithSuchIdExistException(){
        super("Supplier with such ID already exist. Can not create");
    }

}