package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.entities.Materia;

public interface MateriaService {
	
	public List<DatosMaterias> traerMaterias();
	public DatosMaterias traerMateria(String id);
	public void guardarMateria(Materia materia);
	public void deshabilitarMateria(String id);
	public void habilitarMateria(String id);
	public void actualizarMateria(Integer idMateria, Integer docente, String nombre);
}
