package com.tender.tenderwebapi.exceptions.tenderEXP;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class IncorrectProvidedTenderDataException extends RuntimeException{
    public IncorrectProvidedTenderDataException(){
        super("Information provided is incorrect. Check it again before sending (for example: dates)");
    }
}
