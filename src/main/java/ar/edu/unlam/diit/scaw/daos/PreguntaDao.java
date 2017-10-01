package ar.edu.unlam.diit.scaw.daos;

import java.util.List;
import ar.edu.unlam.diit.scaw.entities.DatosPreguntas;
import ar.edu.unlam.diit.scaw.entities.Preguntas;;

public interface PreguntaDao {
	
	public List<DatosPreguntas> getAllPreguntas();
	public void salvarPreguntas(Preguntas pregunta);
	public DatosPreguntas getPregunta(String id);
}
