package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.impl.MateriaServiceImpl;

@ManagedBean(name = "datosMateriasBean", eager = true)
@RequestScoped
public class DatosMateriasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idMateria = null;
	private String nombreMateria = null;
	private String descripcion = null;
	private String docente = null;
	private Integer idDocente = null;
	private Integer idEstadoMateria = null;
	private String idMateriaString = null;
	
	MateriaService servicioMateria;
	
	public DatosMateriasBean(){
		super();
		servicioMateria = (MateriaService) new MateriaServiceImpl();
	}
	
	public String editar(){
		String valor = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMateria");
	
		DatosMaterias materia = servicioMateria.traerMateria(valor);
		
		idMateria = materia.getIdMateria();
		nombreMateria = materia.getNombreMateria();
		docente = materia.getDocente();
		idDocente = materia.getIdDocente();
		
		return "editarMateria";
	}	
	
	public String actualizarMateria(){
		
		String materiaForm = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idMateria");
		String docenteForm = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idDocente");
		String materiaNombreForm = nombreMateria;
		
		Integer idDocente = this.idDocente;
		Integer idMateria = Integer.parseInt(materiaForm);
		
		servicioMateria.actualizarMateria(idMateria, idDocente, materiaNombreForm);
		
		return "welcome";
	}
	
	public Integer getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(Integer idMateria) {
		this.idMateria = idMateria;
	}
	public String getNombreMateria() {
		return nombreMateria;
	}
	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getDocente() {
		return docente;
	}
	public void setDocente(String docente) {
		this.docente = docente;
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




	public String getIdMateriaString() {
		return idMateriaString;
	}




	public void setIdMateriaString(String idMateriaString) {
		this.idMateriaString = idMateriaString;
	}

	public Integer getIdDocente() {
		return idDocente;
	}

	public void setIdDocente(Integer idDocente) {
		this.idDocente = idDocente;
	}
	
	
	
	
	
	

}
