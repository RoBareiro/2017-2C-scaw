package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;

import ar.edu.unlam.diit.scaw.daos.MateriaDao;
import ar.edu.unlam.diit.scaw.daos.impl.MateriaDaoImpl;
import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.services.MateriaService;

public class MateriaServiceImpl implements MateriaService{
	
	MateriaDaoImpl servicioDao;
	
	public MateriaServiceImpl(){
		servicioDao = new MateriaDaoImpl();
	}
	
	@Override
	public List<DatosMaterias> traerMaterias(){
		
		return servicioDao.getAllMaterias();
	}

	public MateriaDao getServicioDao() {
		return servicioDao;
	}

	public void setServicioDao(MateriaDaoImpl servicioDao) {
		this.servicioDao = servicioDao;
	}

	@Override
	public void guardarMateria(Materia materia) {
		servicioDao.salvarMateria(materia);
		
	}
	
	
	@Override
	public void deshabilitarMateria(String id){
		servicioDao.deshabilitar(id);
	}
	
	@Override
	public void habilitarMateria(String id){
		servicioDao.habilitar(id);
	}

	@Override
	public DatosMaterias traerMateria(String id) {
		return servicioDao.getMateria(id);
	}

	@Override
	public void actualizarMateria(Integer idMateria, Integer docente, String nombre) {
		servicioDao.actualizarDatos(idMateria, docente, nombre);
	}

}
