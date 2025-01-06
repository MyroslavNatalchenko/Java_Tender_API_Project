package com.tender.tenderwebapi.exceptions.purchaserEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PurchaserNotFoundException extends RuntimeException{
    public PurchaserNotFoundException(){
        super("No Purchaser with such Tender ID");
    }
}
