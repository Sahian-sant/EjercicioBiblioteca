package com.mx.ApiRestBiblioteca.Entity;




import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USUARIOS")
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//aqui es donde se muestra el codigo de 8 caracteres que sera unico 
    @Column(name = "CODIGO", length = 8, nullable = false, unique = true)
    private String codigo;
  
    private String nuevoCodigo;
}
