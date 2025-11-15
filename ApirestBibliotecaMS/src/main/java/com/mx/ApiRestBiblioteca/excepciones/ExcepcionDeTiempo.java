package com.mx.ApiRestBiblioteca.excepciones;

import lombok.Getter;

@Getter
public class ExcepcionDeTiempo extends RuntimeException {

    private int code;
    private String type;
    private long timestamp;
    private String details;

    public ExcepcionDeTiempo(int code, String details) {
        super(details);
        this.code = code;
        this.type = "Error";
        this.timestamp = System.currentTimeMillis() / 1000;
        this.details = details;
    }
}
