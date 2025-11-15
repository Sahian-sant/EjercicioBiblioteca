package com.mx.ApiRestBiblioteca.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.ApiRestBiblioteca.Entity.Usuario;



@Repository
public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

    
    boolean existsByCodigo(String codigo);

   
    Usuario findByCodigo(String codigo);





	
}
