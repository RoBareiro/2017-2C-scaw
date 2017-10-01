package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String email;
	private String contraseña;
	private String apellido;
	private String nombre;
	private List<Integer> idRol;
	
	

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

	public List<Integer> getIdRol() {
		return idRol;
	}

	public void setIdRol(List<Integer> idRol) {
		this.idRol = idRol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	
}
