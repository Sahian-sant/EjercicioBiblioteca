package com.mx.ApirestBibliotecaMS.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApirestBibliotecaMS.Entity.Usuario;
import com.mx.ApirestBibliotecaMS.service.Ms2Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Ms2Controller {

    private final Ms2Service service;

    @GetMapping("/ms2/usuarios")
    public CompletableFuture<ResponseEntity<List<Usuario>>> listarUsuarios() {
        return service.obtenerUsuarios()
                      .thenApply(ResponseEntity::ok);
    }
}
