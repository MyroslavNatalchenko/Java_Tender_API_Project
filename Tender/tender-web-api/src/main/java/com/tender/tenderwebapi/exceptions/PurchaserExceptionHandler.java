package com.tender.tenderwebapi.exceptions;

import com.tender.tenderwebapi.exceptions.purchaserEXP.CanNotDeletePurchaserException;
import com.tender.tenderwebapi.exceptions.purchaserEXP.PurchaserNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotDeleteTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class PurchaserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value={CanNotDeletePurchaserException.class})
    public ResponseEntity<Object> handleNotFound(CanNotDeletePurchaserException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={PurchaserNotFoundException.class})
    public ResponseEntity<Object> handleCanNotEdit(PurchaserNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
