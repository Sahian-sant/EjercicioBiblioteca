package com.mx.ApiRestBiblioteca.Controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mx.ApiRestBiblioteca.Entity.Usuario;
import com.mx.ApiRestBiblioteca.Service.UsuarioService;
import com.mx.ApiRestBiblioteca.dto.UsuarioDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/biblioteca")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    // Crear usuario
    @PostMapping("/crear")
  //http://localhost:8030/biblioteca/crear
    public ResponseEntity<UsuarioDto> crear(@RequestBody Usuario usuario) {
        Usuario creado = service.registrarEntrada(usuario.getCodigo());
        return ResponseEntity.ok(new UsuarioDto(creado.getId(), creado.getCodigo()));
    }

    // Listar todos los usuarios
    @GetMapping("/listar")
    //http://localhost:8030/biblioteca/listar
    public ResponseEntity<List<UsuarioDto>> listar(
            @RequestHeader(value = "Bandera", defaultValue = "false") String bandera) {

        boolean retraso = bandera.equalsIgnoreCase("true");
        List<Usuario> usuarios = service.getUsuarios(retraso);

        // para no mostrar campos innecesarios
        List<UsuarioDto> usuariosDto = usuarios.stream()
                .map(u -> new UsuarioDto(u.getId(), u.getCodigo()))
                .toList();

        return ResponseEntity.ok(usuariosDto);
    }

    // Obtener usuario
    // http://localhost:8030/biblioteca/obtener?codigo=A1B2C3D4
    @GetMapping("/obtener")
    public ResponseEntity<UsuarioDto> obtener(@RequestParam("codigo") String codigo) {
        Usuario user = service.getUsuario(codigo);
        return ResponseEntity.ok(new UsuarioDto(user.getId(), user.getCodigo()));
    }

    // Actualizar código
  //http://localhost:8030/biblioteca/actualizar
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioDto> actualizar(@RequestBody Usuario usuario) {
        Usuario actualizado = service.actualizarUsuario(usuario.getCodigo(), usuario.getNuevoCodigo());
        return ResponseEntity.ok(new UsuarioDto(actualizado.getId(), actualizado.getCodigo()));
    }

    // Eliminar usuario
     //http://localhost:8030/biblioteca//eliminar
    @DeleteMapping("/eliminar")
    public ResponseEntity<String> eliminar(@RequestBody Usuario usuario) {
        service.eliminarUsuario(usuario.getCodigo());
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }

    // Registrar salida 
    //http://localhost:8030/biblioteca/listar
    @PostMapping("/salida")
    public ResponseEntity<String> salida(@RequestBody Usuario usuario) {
        service.registrarSalida(usuario.getCodigo());
        return ResponseEntity.ok("Usuario salió correctamente");
    }
}

