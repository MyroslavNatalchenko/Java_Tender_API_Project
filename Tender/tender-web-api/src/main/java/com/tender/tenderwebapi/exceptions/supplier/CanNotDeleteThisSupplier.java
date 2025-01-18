package com.tender.tenderwebapi.exceptions.supplier;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CanNotDeleteThisSupplier extends RuntimeException{
    public CanNotDeleteThisSupplier(){
        super("Awarded with such Supplier exist. Can not delete");
    }
}
