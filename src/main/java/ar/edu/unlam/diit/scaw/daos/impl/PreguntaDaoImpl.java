package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.PreguntaDao;
import ar.edu.unlam.diit.scaw.entities.DatosPreguntas;
import ar.edu.unlam.diit.scaw.entities.Preguntas;

public class PreguntaDaoImpl implements PreguntaDao {
	
	HsqlDataSource dataSource = new HsqlDataSource();
	Connection conn;
	
	@Override
	public List<DatosPreguntas> getAllPreguntas() {
		
		List<DatosPreguntas> ll = new LinkedList<DatosPreguntas>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT  pre.pregunta from PUBLIC.PREGUNTAS pre inner join PUBLIC.RESPUESTAS re ON pre.id = re.id");
			 
			while (rs.next()) {
			  
				Integer id = rs.getInt("id");
				Integer idExamen = rs.getInt("idExamen");
				String pregunta =rs.getString("pregunta");
			
								
				DatosPreguntas datos = new DatosPreguntas();
				datos.setId(id);
				datos.setIdExamen(idExamen);
				datos.setPregunta(pregunta);
				ll.add(datos);
				
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	

	@Override
	public void salvarPreguntas(Preguntas pregunta) {
		// TODO Auto-generated method stub
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			
	
			
			query.executeUpdate(
								"INSERT INTO Preguntas (id, idExamen, Pregunta) VALUES(" + pregunta.getId() + "," + pregunta.getIdExamen() + pregunta.getPregunta());
			
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
	@Override
	public DatosPreguntas getPregunta(String id) {
		
		DatosPreguntas datospregunta = new DatosPreguntas();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT  pre.pregunta as preguntas , pre.id as idpregunta ,re.respuesta as respuesta ,re.id as respuestaid,ex.nombre as nombre ,ex.id as idexamen from PUBLIC.PREGUNTAS pre inner join PUBLIC.RESPUESTAS re ON pre.id = re.id   inner join  EXAMENES ex ON pre.id = ex.id");
			
			while (rs.next()) {					
				datospregunta.setIdExamen(rs.getInt("idexamen"));
				datospregunta.setId(rs.getInt("id"));
				datospregunta.setPregunta(rs.getString("preguntas"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datospregunta;
	}

}
