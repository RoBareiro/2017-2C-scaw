package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.entities.Materia;

public interface MateriaDao {
	
	public List<DatosMaterias> getAllMaterias();
	public void salvarMateria(Materia materia);
	void deshabilitar(String id);
	void habilitar(String id);
}
