package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.MateriaDao;
import ar.edu.unlam.diit.scaw.entities.DatosMaterias;
import ar.edu.unlam.diit.scaw.entities.Materia;

public class MateriaDaoImpl implements MateriaDao{
	
	HsqlDataSource dataSource = new HsqlDataSource();
	Connection conn;
	
	@Override
	public List<DatosMaterias> getAllMaterias() {
		
		List<DatosMaterias> ll = new LinkedList<DatosMaterias>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT m.id as idMateria, m.nombre as nombreMateria, m.idEstadoMateria as idEstadoMateria , est.descripcion as descripcion, u.nombre as nombreDocente, u.apellido as apellidoDocente FROM materias as m INNER JOIN estadosmaterias as est ON m.idEstadoMateria = est.id INNER JOIN usuarios as u ON m.idDocenteTitular = u.id");
	
			while (rs.next()) {
			  
				Integer idMateria = rs.getInt("idMateria");
				String nombreMateria = rs.getString("nombreMateria");
				String descripcion = rs.getString("descripcion");
				String nombreDocente = rs.getString("nombreDocente");
				String apellidoDocente = rs.getString("apellidoDocente");
				Integer idEstadoMateria = rs.getInt("idEstadoMateria");
								
				DatosMaterias datos = new DatosMaterias();
				datos.setIdMateria(idMateria);
				datos.setNombreMateria(nombreMateria);
				datos.setDescripcion(descripcion);
				datos.setDocente(nombreDocente + " " + apellidoDocente);
				datos.setIdEstadoMateria(idEstadoMateria);
				
	
				ll.add(datos);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	@Override
	public void salvarMateria(Materia materia) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			String nombre = " '" + materia.getNombre() + "' ";
			// Este es el que va, pero hasta que no este armada la parte de asignacion de docente no se puede usar
			// Integer idDocente = materia.getIdDocenteTitular();
			Integer idDocente = 1;
			
			query.executeUpdate(
								"INSERT INTO Materias (nombre, idDocenteTitular, idEstadoMateria) VALUES(" + nombre + "," + idDocente + ", 1)");  
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void deshabilitar(String id) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			
			query.executeUpdate("UPDATE materias SET idEstadoMateria = 2 WHERE id ='" + id + "'");  
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void habilitar(String id) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			
			query.executeUpdate("UPDATE materias SET idEstadoMateria = 1 WHERE id ='" + id + "'");  
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
}