package com.tender.tenderwebapi.exceptions.tenderEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CanNotDeleteTenderException extends RuntimeException{
    public CanNotDeleteTenderException(){
        super("Can not delete Tender. ID is incorrect");
    }
}
