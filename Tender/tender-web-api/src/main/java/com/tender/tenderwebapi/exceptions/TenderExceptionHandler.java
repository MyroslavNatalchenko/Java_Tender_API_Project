package com.tender.tenderwebapi.exceptions;

import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotDeleteTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.IncorrectProvidedTenderDataException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderWithSuchIdExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TenderExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value={CanNotDeleteTenderException.class})
    public ResponseEntity<Object> handleNotFound(CanNotDeleteTenderException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={TenderNotFoundException.class})
    public ResponseEntity<Object> handleCanNotEdit(TenderNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={IncorrectProvidedTenderDataException.class})
    public ResponseEntity<Object> handleCanNotDelete(IncorrectProvidedTenderDataException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value={TenderWithSuchIdExistException.class})
    public ResponseEntity<Object> handleCanNotCreate(TenderWithSuchIdExistException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}
