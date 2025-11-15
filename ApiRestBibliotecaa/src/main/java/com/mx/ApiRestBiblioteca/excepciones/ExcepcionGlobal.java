package com.mx.ApiRestBiblioteca.excepciones;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcepcionGlobal {

    @ExceptionHandler(ExcepcionDeTiempo.class)
    public ResponseEntity<?> handleGeneric(ExcepcionDeTiempo
    		ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(ex);
    }
}
