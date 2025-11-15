package com.mx.ApirestBibliotecaMS.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;

import com.mx.ApiRestBiblioteca.excepciones.ExcepcionDeTiempo;
import com.mx.ApirestBibliotecaMS.Entity.Usuario;
import com.mx.ApirestBibliotecaMS.cliente.clienteMs1;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Ms2Service {

    private final clienteMs1 ms1Client;

    @CircuitBreaker(name = "ms1Circuit", fallbackMethod = "fallbackUsuarios")
    @TimeLimiter(name = "ms1TimeLimiter")
    public CompletableFuture<List<Usuario>> obtenerUsuarios() {
        return CompletableFuture.supplyAsync(() -> ms1Client.getUsuarios("false"));
    }

    // Fallback
    private CompletableFuture<List<Usuario>> fallbackUsuarios(Throwable ex) {
        throw new ExcepcionDeTiempo(500, "El MS1 tardó más de lo esperado o está fuera de línea: " + ex.getMessage());
    }
}
