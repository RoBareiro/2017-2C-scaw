package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import ar.edu.unlam.diit.scaw.entities.DatosPreguntas;
import ar.edu.unlam.diit.scaw.entities.Preguntas;
import ar.edu.unlam.diit.scaw.services.PreguntaService;
import ar.edu.unlam.diit.scaw.services.impl.PreguntaServiceImpl;

@ManagedBean(name = "preguntaBean", eager = true)
@RequestScoped
public class PreguntaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private Integer idExamen = null;
	private String pregunta = null;
	
	PreguntaService servicioPregunta;
	
	public PreguntaBean(){
		super();
		servicioPregunta = (PreguntaService) new PreguntaServiceImpl(); 
	
	}
	
	
public PreguntaBean(Integer id, Integer idExamen, String pregunta){
		
		this.id = id;
		this.idExamen = idExamen;
		this.pregunta = pregunta;

	}
	
	public List<DatosPreguntas> getAllMaterias(){
		List<DatosPreguntas> lista = servicioPregunta.traerPregunta();
		return lista;	
	}
	
	public String nuevoExamen(){
		return "formularioExamenes";
    }
	

	
	public String guardarPreguntas(){
		Preguntas pregunta = new Preguntas();
		pregunta.setPregunta(getPregunta());	
		servicioPregunta.guardarPregunta(pregunta);
		return "admin";
	}
	

	
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
