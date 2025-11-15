package com.mx.ApirestBibliotecaMS.cliente;


import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mx.ApirestBibliotecaMS.Entity.Usuario;

@FeignClient(name = "ms1", url = "http://localhost:8030")
public interface clienteMs1 {

    @GetMapping("/biblioteca/usuarios")
    List<Usuario> getUsuarios(@RequestHeader(value = "Bandera", defaultValue = "false") String bandera);
}
