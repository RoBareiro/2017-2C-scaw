package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;
import java.util.List;

public class Examenes implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private Integer idMateria;
	private Integer idEstadoExamen;
	private Materia materia;
	private String estadosExamenes;
	private List<Preguntas> preguntas;
	
	
	public List<Preguntas> getPreguntas() {
		return preguntas;
	}
	public void setPreguntas(List<Preguntas> preguntas) {
		this.preguntas = preguntas;
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
	public Integer getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}
	public Integer getIdEstadoExamen() {
		return idEstadoExamen;
	}
	public void setIdEstadoExamen(Integer idEstadoExamen) {
		this.idEstadoExamen = idEstadoExamen;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Materia getMateria() {
		return materia;
	}
	public void setMateria(Materia materia) {
		this.materia = materia;
	}
	public String getEstadosExamenes() {
		return estadosExamenes;
	}
	public void setEstadosExamenes(String estadosExamenes) {
		this.estadosExamenes = estadosExamenes;
	}
	
	
	
	
}
