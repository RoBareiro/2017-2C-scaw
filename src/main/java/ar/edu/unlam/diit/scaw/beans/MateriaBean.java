package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.impl.MateriaServiceImpl;

@ManagedBean(name = "materiaBean", eager = true)
@RequestScoped
public class MateriaBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String nombre = null;
	private Integer idDocenteTitular = null;
	private Integer idEstadoMateria = null;
	
	MateriaService servicioMateria;
	
	public MateriaBean(){
		super();
		servicioMateria = (MateriaService) new MateriaServiceImpl(); 
		
	}
	
	public MateriaBean(Integer id, String nombre, Integer idDocenteTitular, Integer idEstadoMateria){
		
		this.id = id;
		this.nombre = nombre;
		this.idDocenteTitular = idDocenteTitular;
		this.idEstadoMateria = idEstadoMateria;
	}
	
	public List<DatosMaterias> getAllMaterias(){
		List<DatosMaterias> lista = servicioMateria.traerMaterias();
		return lista;	
	}
	
	public String nuevaMateria(){
			return "nuevaMateria";
	}
		 	
	public String guardarMateria(){
		Materia materia = new Materia();
		materia.setNombre(getNombre());
		servicioMateria.guardarMateria(materia);
		return "admin";
	}
	
	public String deshabilitar(){
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMateria");

		servicioMateria.deshabilitarMateria(valor);
		return "admin";
	}
	
	public String habilitar(){
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMateria");

		servicioMateria.habilitarMateria(valor);
		return "admin";
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

	public MateriaService getServicioMateria() {
		return servicioMateria;
	}

	public void setServicioMateria(MateriaService servicioMateria) {
		this.servicioMateria = servicioMateria;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
