package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import ar.edu.unlam.diit.scaw.daos.ExamenDao;
import ar.edu.unlam.diit.scaw.daos.impl.ExamenDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.services.ExamenService;

public class ExamenServiceImpl implements ExamenService {
	
	ExamenDaoImpl servicioDao;
	
	public ExamenServiceImpl(){
		servicioDao = new ExamenDaoImpl();
	}
	
	@Override
	public List<Examenes> traerExamen(){
		
		return servicioDao.getAllExamenes();
	}

	public ExamenDao getServicioDao() {
		return servicioDao;
	}

	public void setServicioDao(ExamenDaoImpl servicioDao) {
		this.servicioDao = servicioDao;
	}

	@Override
	public void guardarExamen(Examenes examen) {
		servicioDao.salvarExamen(examen);
		
	}
	
	@Override
	public Examenes getExamenById(Integer id) {
		return servicioDao.getExamenById(id);
		
	}

	@Override
	public void editarExamen(Examenes examen) {
		servicioDao.editarExamen(examen);
	}

	@Override
	public void deshabilitarExamen(Integer id) {
		servicioDao.deshabilitarExamen(id);
	}

	@Override
	public List<Examenes> traerExamenActivos() {
		return servicioDao.traerExamenActivos();
	}
	

}
