package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;
import ar.edu.unlam.diit.scaw.daos.PreguntaDao;
import ar.edu.unlam.diit.scaw.daos.impl.PreguntaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.DatosPreguntas;
import ar.edu.unlam.diit.scaw.entities.Preguntas;
import ar.edu.unlam.diit.scaw.services.PreguntaService;

public class PreguntaServiceImpl implements PreguntaService {
	
	PreguntaDaoImpl servicioDao;
	
	public PreguntaServiceImpl(){
		servicioDao = new PreguntaDaoImpl();
	}
	
	@Override
	public List<DatosPreguntas> traerPregunta(){
		
		return servicioDao.getAllPreguntas();
	}

	public PreguntaDao getServicioDao() {
		return servicioDao;
	}

	public void setServicioDao(PreguntaDaoImpl servicioDao) {
		this.servicioDao = servicioDao;
	}
	
	@Override
	public DatosPreguntas traerPregunta(String id) {
		return servicioDao.getPregunta(id);
	}


	@Override
	public void guardarPregunta(Preguntas pregunta) {
		// TODO Auto-generated method stub
		servicioDao.salvarPreguntas(pregunta);
		
	}

}
