package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.entities.Materia;

public interface MateriaDao {
	
	public List<DatosMaterias> getAllMaterias();
	public void salvarMateria(Materia materia);
	public void deshabilitar(String id);
	public void habilitar(String id);
	public DatosMaterias getMateria(String id);
	public void actualizarDatos (Integer idMateria, Integer docente, String nombre);
}
