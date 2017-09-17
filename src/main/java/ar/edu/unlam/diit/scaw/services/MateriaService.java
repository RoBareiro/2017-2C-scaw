package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.entities.Materia;

public interface MateriaService {
	
	public List<DatosMaterias> traerMaterias();
	public void guardarMateria(Materia materia);
	void deshabilitarMateria(String id);
	void habilitarMateria(String id);
}
