package com.tender.tenderwebapi.exceptions.tenderEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class TenderWithSuchIdExistException extends RuntimeException{
    public TenderWithSuchIdExistException(){
        super("Tender with such ID already exist. Can not create");
    }

}
