package org.skypro.skyshop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StoreControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<StoreError> handleNoSuchProductException(NoSuchProductException ex) {
        StoreError error = new StoreError("1488", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
