package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
			// Creo la consulta SQL
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Materias (nombre, idDocenteTitular, idEstadoMateria) VALUES(?,?,1)");
			ps.setString(1, materia.getNombre());
			ps.setInt(2, materia.getIdDocenteTitular());
			
			// Ejecuto la sentencia
			ps.executeUpdate();
			ps.close();
			
			/*
			Statement query;
			
			query = conn.createStatement();
			
			String nombre = " '" + materia.getNombre() + "' ";
			
			query.executeUpdate("INSERT INTO Materias (nombre, idDocenteTitular, idEstadoMateria) VALUES(" + nombre + "," + materia.getIdDocenteTitular() + ", 1)");  
			*/
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void deshabilitar(String id) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			// Creo la consulta SQL
			PreparedStatement ps = conn.prepareStatement("UPDATE materias SET idEstadoMateria = 2 WHERE id = ? ");
			ps.setString(1, id);
						
			// Ejecuto la sentencia
			ps.executeUpdate();
			ps.close();
						  
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void habilitar(String id) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			// Creo la consulta SQL
			PreparedStatement ps = conn.prepareStatement("UPDATE materias SET idEstadoMateria = 1 WHERE id = ? ");
			ps.setString(1, id);
			
			// Ejecuto la sentencia
			ps.executeUpdate();
			ps.close();
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public DatosMaterias getMateria(String id) {
		
		DatosMaterias datos = new DatosMaterias();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT m.id as idMateria, m.nombre as nombreMateria, m.idEstadoMateria as idEstadoMateria , est.descripcion as descripcion, u.nombre as nombreDocente,u.id as idDocente, u.apellido as apellidoDocente FROM materias as m INNER JOIN estadosmaterias as est ON m.idEstadoMateria = est.id INNER JOIN usuarios as u ON m.idDocenteTitular = u.id WHERE m.id = '" + id + "'");
			
			while (rs.next()) {					
				datos.setIdMateria(rs.getInt("idMateria"));
				datos.setNombreMateria(rs.getString("nombreMateria"));
				datos.setDocente(rs.getString("nombreDocente") + " " + rs.getString("apellidoDocente"));
				datos.setIdDocente(rs.getInt("idDocente"));
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datos;
	}

	@Override
	public void actualizarDatos(Integer idMateria, Integer docente, String nombre) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			// Creo la consulta SQL
			PreparedStatement ps = conn.prepareStatement("UPDATE materias SET nombre = ? , idDocenteTitular = ? WHERE id=? ");
			
			// Seteo las variables parametrizadas
			ps.setString(1, nombre);
			ps.setInt(2, docente);
			ps.setInt(3, idMateria);
			
			// Ejecuto la sentencia
			ps.executeUpdate();
			ps.close();
							    
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
