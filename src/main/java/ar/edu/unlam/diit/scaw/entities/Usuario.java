package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String contraseña;
	private String apellido;
	private String nombre;
	private Integer idEstadoUsuario;

/*************************************************************************************************************/	
	
	public Integer getIdEstadoUsuario() {
		return idEstadoUsuario;
	}

	public void setIdEstadoUsuario(Integer idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
	}

/*************************************************************************************************************/	
	
	public Usuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

}
