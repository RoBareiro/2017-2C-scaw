package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Examenes;

public interface ExamenService {
	
	public List<Examenes> traerExamen();
	public void guardarExamen(Examenes examen);

}
