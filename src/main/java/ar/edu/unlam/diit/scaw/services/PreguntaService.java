package ar.edu.unlam.diit.scaw.services;

import java.util.List;
import ar.edu.unlam.diit.scaw.entities.DatosPreguntas;
import ar.edu.unlam.diit.scaw.entities.Preguntas;;

public interface PreguntaService {
	
	public List<DatosPreguntas> traerPregunta();
	public void guardarPregunta(Preguntas pregunta);
	public DatosPreguntas traerPregunta(String id);
}
