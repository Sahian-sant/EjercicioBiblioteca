package com.mx.ApiRestBiblioteca.Service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.mx.ApiRestBiblioteca.Entity.Usuario;
import com.mx.ApiRestBiblioteca.excepciones.ExcepcionDeTiempo;
import com.mx.ApiRestBiblioteca.repository.UsuariosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuariosRepository repository;
    private static final int MAX_CAPACITY = 10;

    private void validarCodigo(String codigo) {
        if (codigo == null || codigo.length() != 8)
            throw new ExcepcionDeTiempo(422, "El código debe tener 8 caracteres");

        if (!codigo.matches("^[a-zA-Z0-9]{8}$"))
            throw new ExcepcionDeTiempo(422, "Código inválido, solo letras o números");

        if (codigo.matches("^[a-zA-Z]{8}$"))
            throw new ExcepcionDeTiempo(422, "El código no puede ser solo letras");
    }

    public Usuario registrarEntrada(String codigo) {
        validarCodigo(codigo);
        if (repository.existsByCodigo(codigo))
            throw new ExcepcionDeTiempo(401, "El Usuario ya está dado de alta");

        if (repository.count() >= MAX_CAPACITY)
            throw new ExcepcionDeTiempo(401, "Aforo máximo alcanzado");

        Usuario user = new Usuario();
        user.setCodigo(codigo);
        return repository.save(user);
    }

    public List<Usuario> getUsuarios(boolean delay) {
        if (delay) {
            try { Thread.sleep(8000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
        return repository.findAll();
    }

    public Usuario getUsuario(String codigo) {
        Usuario user = repository.findByCodigo(codigo);
        if (user == null)
            throw new ExcepcionDeTiempo(404, "Usuario no encontrado");
        return user;
    }

    public Usuario actualizarUsuario(String codigo, String nuevoCodigo) {
        Usuario user = repository.findByCodigo(codigo);
        if (user == null)
            throw new ExcepcionDeTiempo(404, "Usuario no encontrado");

        validarCodigo(nuevoCodigo);

        if (!codigo.equals(nuevoCodigo) && repository.existsByCodigo(nuevoCodigo))
            throw new ExcepcionDeTiempo(422, "El nuevo código ya está en uso");

        user.setCodigo(nuevoCodigo);
        return repository.save(user);
    }

    public void eliminarUsuario(String codigo) {
        Usuario user = repository.findByCodigo(codigo);
        if (user == null)
            throw new ExcepcionDeTiempo(404, "Usuario no encontrado");
        repository.delete(user);
    }

    public void registrarSalida(String codigo) {
        eliminarUsuario(codigo);
    }
}

