package com.tender.tenderwebapi.exceptions;

import com.tender.tenderwebapi.exceptions.awardedEXP.NoAwardedWithSuchID;
import com.tender.tenderwebapi.exceptions.purchaserEXP.CanNotDeletePurchaserException;
import com.tender.tenderwebapi.exceptions.purchaserEXP.PurchaserNotFoundException;
import com.tender.tenderwebapi.exceptions.supplier.CanNotDeleteThisSupplier;
import com.tender.tenderwebapi.exceptions.supplier.NoSupplerWithSuchIdException;
import com.tender.tenderwebapi.exceptions.supplier.SupplerWithSuchIdExistException;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotDeleteTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.IncorrectProvidedTenderDataException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderWithSuchIdExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(value={CanNotDeleteTenderException.class})
    public ResponseEntity<Object> handleNotFound(CanNotDeleteTenderException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={TenderNotFoundException.class})
    public ResponseEntity<Object> handleCanNotEdit(TenderNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={IncorrectProvidedTenderDataException.class})
    public ResponseEntity<Object> handleCanNotDelete(IncorrectProvidedTenderDataException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={TenderWithSuchIdExistException.class})
    public ResponseEntity<Object> handleCanNotCreate(TenderWithSuchIdExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={CanNotDeletePurchaserException.class})
    public ResponseEntity<Object> handleNotFound(CanNotDeletePurchaserException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={PurchaserNotFoundException.class})
    public ResponseEntity<Object> handleCanNotEdit(PurchaserNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={NoAwardedWithSuchID.class})
    public ResponseEntity<Object> handleNotFound(NoAwardedWithSuchID ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={SupplerWithSuchIdExistException.class})
    public ResponseEntity<Object> handleCanNotCreate(SupplerWithSuchIdExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={NoSupplerWithSuchIdException.class})
    public ResponseEntity<Object> handleCanNotEdit(NoSupplerWithSuchIdException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value={CanNotDeleteThisSupplier.class})
    public ResponseEntity<Object> handleCanNotDelete(CanNotDeleteThisSupplier ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
