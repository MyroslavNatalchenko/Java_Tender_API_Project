package com.tender.tenderwebapi.exceptions.awardedEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoAwardedWithSuchID extends RuntimeException{
    public NoAwardedWithSuchID(){
        super("No Awarded with such ID");
    }
}