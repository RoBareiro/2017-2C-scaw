package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.entities.Preguntas;
import ar.edu.unlam.diit.scaw.entities.Respuestas;
import ar.edu.unlam.diit.scaw.services.ExamenService;
import ar.edu.unlam.diit.scaw.services.impl.ExamenServiceImpl;

@ManagedBean(name = "examenBean", eager = true)
@RequestScoped
public class ExamenBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer id = null;
	private String nombre = null;
	private String pregunta1;
	private Integer idMateria = null;
	private Integer idEstadoExamen = null;
	private Integer estadoExamen = 0;
	private List<Preguntas> preguntas = new ArrayList<>();
	DataModel<Examenes> lista ;
	private Examenes examenSelected = new Examenes();
	private Double resultExamen;
	
	ExamenService servicioExamen;
	
	public ExamenBean(){
		super();
		servicioExamen = (ExamenService) new ExamenServiceImpl(); 
	
		preguntas.add(new Preguntas(0,0,"",0));
		preguntas.add(new Preguntas(0,0,"",0));
		preguntas.add(new Preguntas(0,0,"",0));
		preguntas.add(new Preguntas(0,0,"",0));
		preguntas.add(new Preguntas(0,0,"",0));
	}
	
	public String editarExamen() {
		
		this.examenSelected = servicioExamen.getExamenById(this.lista.getRowData().getId());
		this.nombre 		= this.examenSelected.getNombre();
		this.idMateria		= this.examenSelected.getIdMateria();
		this.idEstadoExamen	= this.examenSelected.getIdEstadoExamen();
		this.preguntas		= this.examenSelected.getPreguntas();
		return "formularioExamenes";
	}
	
	
	public DataModel<Examenes> getAllExamenes(){
		this.lista = new ListDataModel<Examenes>(servicioExamen.traerExamen());

		return this.lista;	
	}
	
	public DataModel<Examenes> getAllExamenesActivos(){
		this.lista = new ListDataModel<Examenes>(servicioExamen.traerExamenActivos());

		return this.lista;	
	}
		
	public String guardarExamen(){
		boolean err=false;
		List<Preguntas> pregDef = new ArrayList<>();
		for (int i=0;i<preguntas.size();i++) {
			Preguntas preg = preguntas.get(i);
			if (preg.getPregunta().equals("") && i==0) {
				err=true;
				FacesContext.getCurrentInstance().addMessage("form:j_idt16:"+i+":pregunta", new FacesMessage("Debe completar al menos una pregunta"));
			} else if (!preg.getPregunta().equals("")) {
				if (this.validateRespuestas(preg.getRespuestas())) {
					preg.setRespuestas(this.getRespuestasOk(preg.getRespuestas()));
					//la pregunta esta OK y tiene respuestas
					pregDef.add(preg);
				} else {
					err=true;
					FacesContext.getCurrentInstance().addMessage("form:j_idt16:"+i+":pregunta", new FacesMessage("Las preguntas deben tener al menos 1 respuesta correcta y uno incorrecta"));
				}
				
			}
		}
		if (pregDef.size()<5) {
			err=true;
			FacesContext.getCurrentInstance().addMessage("form:nombreExamen", new FacesMessage("Debe completar al menos 5 preguntas"));
		}
		
		if (err!=true) {
			Examenes examenes = new Examenes();
			examenes.setNombre(this.nombre);
			examenes.setIdMateria(idMateria);
			examenes.setIdEstadoExamen(idEstadoExamen);
			examenes.setPreguntas(pregDef);
			//examenes.setPreguntas(preguntas);
			
			if (this.examenSelected.getId()==null) {
				servicioExamen.guardarExamen(examenes);
			} else {
				examenes.setId(this.examenSelected.getId());
				servicioExamen.editarExamen(examenes);
			}

			return "gestionExamenes";	
		}
		return "formularioExamenes";
	}
	

	public String getTextSalvarExamen() {
		if (this.examenSelected.getId()==null) {
			return "Agregar nuevo Examen";
		} else {
			return "Modificar Examen";
		}
	}

	private List<Respuestas> getRespuestasOk (List<Respuestas> resp) {
		List<Respuestas> def = new ArrayList<>();
		for (int i=0;i<resp.size();i++) {
			if (!resp.get(i).getRespuesta().equals("")) {
				def.add(resp.get(i));
			}
		}
		return def;
	}
	private boolean validateRespuestas (List<Respuestas> resp) {
		int ok=0;
		int fail=0;
		int fatal=0;
		for (int i=0;i<resp.size();i++) {
			if (!resp.get(i).getRespuesta().equals("")) {
				if (resp.get(i).getIdTipoRespuesta()!=null && resp.get(i).getIdTipoRespuesta().equals(1)) {
					ok++;
				} else if (resp.get(i).getIdTipoRespuesta()!=null &&resp.get(i).getIdTipoRespuesta().equals(2)) {
					fail++;
				} else if (resp.get(i).getIdTipoRespuesta()==null) {
					fatal++;
				}
			}
		}
		if ((ok>=1 && fail>=1) && fatal==0) {
			return true;
		}
		return false;
	}

	public String rendirExamen() {
		this.examenSelected = servicioExamen.getExamenById(this.lista.getRowData().getId());
		this.nombre 		= this.examenSelected.getNombre();
		this.idMateria		= this.examenSelected.getIdMateria();
		this.idEstadoExamen	= this.examenSelected.getIdEstadoExamen();
		this.preguntas		= this.examenSelected.getPreguntas();

		return "rendirExamen";
	}
	
	public String verResultadoExamen() {
		this.examenSelected = servicioExamen.getExamenById(this.examenSelected.getId());
		this.nombre 		= this.examenSelected.getNombre();
		this.idMateria		= this.examenSelected.getIdMateria();
		this.idEstadoExamen	= this.examenSelected.getIdEstadoExamen();
		//this.preguntas		= this.examenSelected.getPreguntas();
		
		double ok = 0;
		double cant = 0;
		int ind = 0;
		for (int i=0;i<this.examenSelected.getPreguntas().size();i++) {
			for (int j=0;j<this.examenSelected.getPreguntas().get(i).getRespuestas().size();j++) {
				cant++;
				if (this.examenSelected.getPreguntas().get(i).getRespuestas().get(j).getIdTipoRespuesta().equals(this.preguntas.get(i).getRespuestas().get(j).getIdTipoRespuestaByAlumno())) {
					ok++;
				}
			}
		}
		this.resultExamen = ok/cant;
		return "resultadoExamen";
	}
	
	public String deshabilitarExamen() {
		
		servicioExamen.deshabilitarExamen(this.lista.getRowData().getId());
		return "";
	}
	public String listarRendirExamen() {
		return "listadoExamenesRendir";
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


	public String getPregunta1() {
		return pregunta1;
	}


	public void setPregunta1(String pregunta1) {
		this.pregunta1 = pregunta1;
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


	public Integer getEstadoExamen() {
		return estadoExamen;
	}


	public void setEstadoExamen(Integer estadoExamen) {
		this.estadoExamen = estadoExamen;
	}


	public List<Preguntas> getPreguntas() {
		return preguntas;
	}


	public void setPreguntas(List<Preguntas> preguntas) {
		this.preguntas = preguntas;
	}

	public Examenes getExamenSelected() {
		return examenSelected;
	}

	public void setExamenSelected(Examenes examenSelected) {
		this.examenSelected = examenSelected;
	}

	public Double getResultExamen() {
		return resultExamen;
	}

	public int getResultExamen2() {
		System.out.println(resultExamen);
		System.err.println((int) Math.ceil((resultExamen) * 100));
		return (int) Math.ceil((resultExamen) * 100);
	}
	
	public void setResultExamen(Double resultExamen) {
		this.resultExamen = resultExamen;
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	
}
