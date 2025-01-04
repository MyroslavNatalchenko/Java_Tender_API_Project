package com.tender.tenderwebapi.exceptions.tenderEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CanNotEditTenderException extends RuntimeException{
    public CanNotEditTenderException(){
        super("Can not edit Tender. Please, check if your data is fine");
    }
}
