package com.tender.tenderwebapi.exceptions.tenderEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TenderNotFoundException extends RuntimeException{
    public TenderNotFoundException(){
        super("No Tender with such ID");
    }
}
