package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;

@ManagedBean(name = "examenBean", eager = true)
@RequestScoped
public class ExamenBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String nombre = null;
	private Integer idMateria = null;
	private Integer idEstadoExamen = null;
	
	ExamenService servicioExamen;
	
	public ExamenBean(){
		super();
		servicioExamen = (ExamenService) new ExamenServiceImpl(); 
	
	}
	
	
public ExamenBean(Integer id, String nombre, Integer idDocenteTitular, Integer idEstadoMateria){
		
		this.id = id;
		this.nombre = nombre;
		this.idMateria = idDocenteTitular;
		this.idEstadoExamen = idEstadoMateria;
	}
	
	public List<Examenes> getAllMaterias(){
		List<Examenes> lista = servicioExamen.traerExamen();
		return lista;	
	}
	
		
	public String guardarExamen(){
		Examenes examenes = new Examenes();
		examenes.setNombre(getNombre());
		servicioExamen.guardarExamen(examenes);
		return "welcome";
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getIdDocenteTitular() {
		return idMateria;
	}
	public void setIdDocenteTitular(Integer idMateria) {
		this.idMateria = idMateria;
	}
	public Integer getIdEstadoExamen() {
		return idEstadoExamen;
	}
	public void setIdEstadoMateria(Integer idEstadoExamen) {
		this.idEstadoExamen = idEstadoExamen;
	}
	
	public ExamenService getServicioExamen() {
		return servicioExamen;
	}

	public void setServicioMateria(ExamenService servicioExamen) {
		this.servicioExamen = servicioExamen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
