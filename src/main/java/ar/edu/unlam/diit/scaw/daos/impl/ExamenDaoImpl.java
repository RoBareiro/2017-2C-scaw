package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.ExamenDao;
import ar.edu.unlam.diit.scaw.entities.Examenes;

public class ExamenDaoImpl implements ExamenDao {
	
	HsqlDataSource dataSource = new HsqlDataSource();
	Connection conn;
	
	@Override
	public List<Examenes> getAllExamenes() {
		
		List<Examenes> ll = new LinkedList<Examenes>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT m.id as idMateria, m.nombre as nombreMateria, est.descripcion as descripcion, u.nombre as nombreDocente, u.apellido as apellidoDocente FROM materias as m INNER JOIN estadosmaterias as est ON m.idEstadoMateria = est.id INNER JOIN usuarios as u ON m.idDocenteTitular = u.id");
	
			while (rs.next()) {
			  
				Integer id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Integer idMateria = rs.getInt("idMateria");
				Integer idEstadoExamen = rs.getInt("idEstadoExamen");
								
				Examenes examen = new Examenes();
				examen.setId(id);
				examen.setNombre(nombre);
				examen.setIdMateria(idMateria);
				examen.setIdEstadoExamen(idEstadoExamen);
	
				ll.add(examen);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	@Override
	public void salvarExamen(Examenes examen) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			String nombre = " '" + examen.getNombre() + "' ";
			Integer idmateria = examen.getIdMateria();
			
			query.executeUpdate(
								"INSERT INTO Examenes (nombre, idMateria, idEstadoExamen) VALUES(" + nombre + "," + idmateria + ", 1)");  
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

}
