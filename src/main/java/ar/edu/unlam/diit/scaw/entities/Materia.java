package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class Materia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private Integer idDocenteTitular;
	private Integer idEstadoMateria;
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
	public Integer getIdDocenteTitular() {
		return idDocenteTitular;
	}
	public void setIdDocenteTitular(Integer idDocenteTitular) {
		this.idDocenteTitular = idDocenteTitular;
	}
	public Integer getIdEstadoMateria() {
		return idEstadoMateria;
	}
	public void setIdEstadoMateria(Integer idEstadoMateria) {
		this.idEstadoMateria = idEstadoMateria;
	}
	
	
}
