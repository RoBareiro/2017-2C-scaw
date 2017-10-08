package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Examenes;

public interface ExamenDao {
	
	public List<Examenes> getAllExamenes();
	public void salvarExamen(Examenes examen);
	public void editarExamen(Examenes examen);
	public Examenes getExamenById(Integer id);
	public void deshabilitarExamen(Integer id);
	public List<Examenes> traerExamenActivos();
}
