package com.tender.tenderwebapi.exceptions.purchaserEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CanNotDeletePurchaserException extends RuntimeException{
    public CanNotDeletePurchaserException(){
        super("Can not delete Purchaser. Tender ID is incorrect");
    }
}
