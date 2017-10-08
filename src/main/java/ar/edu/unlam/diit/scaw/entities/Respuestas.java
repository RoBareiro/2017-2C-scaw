package ar.edu.unlam.diit.scaw.entities;

import java.io.Serializable;

public class Respuestas implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer idPregunta;
	private String 	respuesta;
	private Integer idTipoRespuesta = 0;
	private Integer idTipoRespuestaByAlumno = 0;
	
	public Respuestas(Integer id,Integer idPregunta,String respuesta,Integer idTipoRespuesta) {
		this.id 				= id;
		this.idPregunta			= idPregunta;
		this.respuesta			= respuesta;
		this.idTipoRespuesta	= idTipoRespuesta;
	}
	
	public Respuestas() {

	}
	
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

	public Integer getIdTipoRespuestaByAlumno() {
		return idTipoRespuestaByAlumno;
	}

	public void setIdTipoRespuestaByAlumno(Integer idTipoRespuestaByAlumno) {
		this.idTipoRespuestaByAlumno = idTipoRespuestaByAlumno;
	}


}
