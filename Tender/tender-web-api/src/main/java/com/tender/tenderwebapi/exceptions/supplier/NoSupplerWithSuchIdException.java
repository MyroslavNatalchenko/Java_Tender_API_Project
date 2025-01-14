package com.tender.tenderwebapi.exceptions.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSupplerWithSuchIdException extends RuntimeException{
    public NoSupplerWithSuchIdException(){
        super("No Purchaser with such Tender ID");
    }
}