package ar.edu.unlam.diit.scaw.entities;

public class Materias {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nombre;
	private Integer idDocente;
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
	public Integer getIdDocente() {
		return idDocente;
	}
	public void setIdDocente(Integer idDocente) {
		this.idDocente = idDocente;
	}
	public Integer getIdEstadoMateria() {
		return idEstadoMateria;
	}
	public void setIdEstadoMateria(Integer idEstadoMateria) {
		this.idEstadoMateria = idEstadoMateria;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
