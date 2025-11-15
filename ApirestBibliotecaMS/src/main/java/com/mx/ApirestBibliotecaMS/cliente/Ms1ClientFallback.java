package com.mx.ApirestBibliotecaMS.cliente;



import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

import com.mx.ApirestBibliotecaMS.Entity.Usuario;

@Component
public class Ms1ClientFallback implements clienteMs1 {

    @Override
    public List<Usuario> getUsuarios(String bandera) {
        throw new RuntimeException("MS1 no disponible o tardó más de 5 segundos");
    }
}
