package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Examenes;

public interface ExamenDao {
	
	public List<Examenes> getAllExamenes();
	public void salvarExamen(Examenes examen);

}
