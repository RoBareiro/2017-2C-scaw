package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class Respuestas implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idPregunta;
	private String respuesta;
	private Integer idTipoRespuesta;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	public String getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	public Integer getIdTipoRespuesta() {
		return idTipoRespuesta;
	}
	public void setIdTipoRespuesta(Integer idTipoRespuesta) {
		this.idTipoRespuesta = idTipoRespuesta;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
