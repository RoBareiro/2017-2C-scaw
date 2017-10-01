package ar.edu.unlam.diit.scaw.entities;

import javax.faces.context.FacesContext;

public class DatosPreguntas {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idExamen;
	private String pregunta;
	private String materia;
	
	
	

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdExamen() {
		return idExamen;
	}
	public void setIdExamen(Integer idExamen) {
		this.idExamen = idExamen;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
