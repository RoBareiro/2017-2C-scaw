package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.ExamenDao;
import ar.edu.unlam.diit.scaw.entities.Examenes;
import ar.edu.unlam.diit.scaw.entities.Materia;
import ar.edu.unlam.diit.scaw.entities.Preguntas;
import ar.edu.unlam.diit.scaw.entities.Respuestas;

public class ExamenDaoImpl implements ExamenDao {
	
	HsqlDataSource dataSource = new HsqlDataSource();
	Connection conn;
	
	@Override
	public List<Examenes> getAllExamenes() {
		
		List<Examenes> ll = new ArrayList<Examenes>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			String sql = "SELECT e.id,e.nombre as examen,m.nombre as materia,ee.descripcion as estado FROM examenes as e INNER JOIN materias as m ON m.id = e.idmateria INNER JOIN estadosexamenes as ee ON e.idestadoexamen=ee.id";
			System.out.println(sql);
			ResultSet rs = query.executeQuery(sql);
	
			while (rs.next()) {
			  
				Examenes examen = new Examenes();
				examen.setId(rs.getInt("id"));
				examen.setNombre(rs.getString("examen"));
				examen.setMateria(new Materia(rs.getString("materia")));
				examen.setEstadosExamenes(rs.getString("estado"));

	
				ll.add(examen);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	@Override
	public Examenes getExamenById(Integer id) {
		Examenes examen = new Examenes();
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			String sql = "SELECT e.id,e.nombre as examen,e.idmateria,e.idestadoexamen,m.nombre as materia FROM examenes as e inner join materias m ON e.idmateria=m.id where e.id="+id;
			System.out.println(sql);
			ResultSet rs = query.executeQuery(sql);
	
			while (rs.next()) {
			  
				
				examen.setId(rs.getInt("id"));
				examen.setNombre(rs.getString("examen"));
				examen.setIdMateria(Integer.valueOf(rs.getString("idmateria")));
				examen.setMateria(new Materia(rs.getString("materia")));
				examen.setIdEstadoExamen(Integer.valueOf(rs.getString("idestadoexamen")));
				

			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		examen.setPreguntas(getPreguntasByIdExamen(examen.getId()));
		return examen;
	}
	
	
	private List<Preguntas> getPreguntasByIdExamen(Integer id) {
		List<Preguntas> pregs = new ArrayList<>();
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			String sql = "SELECT p.id,p.pregunta FROM preguntas as p where p.idexamen="+id;
			System.out.println(sql);
			ResultSet rs = query.executeQuery(sql);
	
			while (rs.next()) {
			  
				Preguntas preg = new Preguntas();
				preg.setId(rs.getInt("id"));
				preg.setPregunta(rs.getString("pregunta"));
				preg.setIdExamen(id);
				preg.setRespuestas(getRespuestasByIdPreg(rs.getInt("id")));
				pregs.add(preg);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pregs;
	}
	
	private List<Respuestas> getRespuestasByIdPreg(Integer id) {
		List<Respuestas> resps = new ArrayList<>();
		try {
		
			Statement query;
			
			query = conn.createStatement();
			String sql = "SELECT r.id,r.respuesta,r.idtiporespuesta FROM respuestas as r where r.idpregunta="+id;
			System.out.println(sql);
			ResultSet rs = query.executeQuery(sql);
	
			while (rs.next()) {
			  
				Respuestas resp = new Respuestas();
				resp.setId(rs.getInt("id"));
				resp.setRespuesta(rs.getString("respuesta"));
				resp.setIdPregunta(id);
				resp.setIdTipoRespuesta(Integer.valueOf(rs.getString("idtiporespuesta")));
				resps.add(resp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resps;
	}
	
	@Override
	public void salvarExamen(Examenes examen) {
		try {
			Integer id = this.getIdExamen();
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			String nombre = " '" + examen.getNombre() + "' ";
			Integer idmateria = examen.getIdMateria();
			String sql = "INSERT INTO Examenes (id,nombre, idMateria, idEstadoExamen) VALUES("+id+"," + nombre + "," + idmateria + ", "+examen.getIdEstadoExamen()+")";
			System.out.println(sql);
			query.executeUpdate(sql);  
			try {
				insertPreguntas(id,examen.getPreguntas());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*for (Preguntas item : examen.getPreguntas()) {
				System.out.println(item.getPregunta());
			}*/
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	private Integer getIdExamen() {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT id FROM examenes order by id desc");
			Integer id =0;
			while (rs.next()) {
				id = rs.getInt("id");
				break;
			}

			return ++id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private Integer getIdPregunta() {
		try {
			
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT id FROM preguntas order by id desc");
			Integer id =0;
			while (rs.next()) {
				id = rs.getInt("id");
				break;
			}

			return ++id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private Integer getIdRespuesta() {
		try {
			//conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT id FROM respuestas order by id desc");
			Integer id =0;
			while (rs.next()) {
				id = rs.getInt("id");
				break;
			}

			return ++id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	private void insertPreguntas(Integer idexamen,List<Preguntas> preguntas) throws SQLException, InterruptedException {
		Statement query;
		query = conn.createStatement();
		
		Integer id = this.getIdPregunta();
		
		for (Preguntas preg : preguntas) {
			String sql = "INSERT INTO preguntas (id,idexamen, pregunta) VALUES("+id+"," + idexamen + ",'" + preg.getPregunta() + "')";
			System.out.println(sql);
			query.executeUpdate(sql);
			conn.commit();
			this.insertRespuestas(id,preg.getRespuestas(),query);
			id++;
		}
	}
	
	private void insertRespuestas(Integer idpregunta,List<Respuestas> respuestas,Statement query) throws SQLException, InterruptedException {
		Integer id = this.getIdRespuesta();
		
		for (Respuestas resp : respuestas) {
			String sql = "INSERT INTO respuestas (id,idpregunta, respuesta,idtiporespuesta) VALUES("+id+"," + idpregunta + ",'" + resp.getRespuesta() + "',"+resp.getIdTipoRespuesta()+")";
			id++;
			System.out.println(sql);
			query.executeUpdate(sql);
		}
	}

	@Override
	public void editarExamen(Examenes examen) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			String sql = "UPDATE Examenes set nombre='" + examen.getNombre() + "',idMateria="+examen.getIdMateria()+", idEstadoExamen="+examen.getIdEstadoExamen()+" WHERE id="+examen.getId();
			System.out.println(sql);
			query.executeUpdate(sql);  
			
			sql = "DELETE FROM respuestas as r WHERE r.idpregunta IN (SELECT p.id FROM Preguntas as p WHERE p.idexamen="+examen.getId()+")";
			System.out.println(sql);
			query.executeUpdate(sql);  
			
			sql = "DELETE FROM preguntas as p WHERE p.idexamen="+examen.getId();
			System.out.println(sql);
			query.executeUpdate(sql);
			//conn.commit();
			try {
				insertPreguntas(examen.getId(),examen.getPreguntas());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void deshabilitarExamen(Integer id) {
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			String sql = "UPDATE Examenes set idEstadoExamen=3 WHERE id="+id;
			System.out.println(sql);
			query.executeUpdate(sql);  
			
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Examenes> traerExamenActivos() {
		List<Examenes> ll = new ArrayList<Examenes>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			String sql = "SELECT e.id,e.nombre as examen,m.nombre as materia,ee.descripcion as estado "+
					"FROM examenes as e "+
					"INNER JOIN materias as m ON m.id = e.idmateria "+
					"INNER JOIN estadosexamenes as ee ON e.idestadoexamen=ee.id "+
					"WHERE e.idestadoexamen!=3";
			System.out.println(sql);
			ResultSet rs = query.executeQuery(sql);
	
			while (rs.next()) {
			  
				Examenes examen = new Examenes();
				examen.setId(rs.getInt("id"));
				examen.setNombre(rs.getString("examen"));
				examen.setMateria(new Materia(rs.getString("materia")));
				examen.setEstadosExamenes(rs.getString("estado"));

	
				ll.add(examen);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}

}
