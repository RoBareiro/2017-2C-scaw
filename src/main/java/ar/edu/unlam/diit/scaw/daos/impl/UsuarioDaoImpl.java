package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.sql.Statement;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {

	HsqlDataSource dataSource = new HsqlDataSource();
	Connection conn;

	@Override
	public Usuario login(Usuario usuario){
		Usuario logueado = null;
		List<Integer> roles = new ArrayList<Integer>() ;
		try{
			conn = (dataSource.dataSource()).getConnection();
			Statement query = conn.createStatement();
			
			String sql = "SELECT * FROM Usuarios U "
					+ "INNER JOIN ROLESUSUARIOS RU " 
					+ "ON U.ID = RU.IDUSUARIO "
					+ "WHERE U.eMail = "+ usuario.getEmail() +
					" AND U.contraseña = "+ usuario.getContraseña() +
					" AND U.idEstadoUsuario = 2";
			ResultSet rs = query.executeQuery(sql);
			while(rs.next()){
				String eMail = rs.getString("eMail");
				String contraseña = rs.getString("contraseña");
				Integer id = rs.getInt("id");
				String apellido = rs.getString("apellido");
				String nombre = rs.getString("nombre");
				Integer idRol = rs.getInt("idRol");
				
				logueado = new Usuario();
				logueado.setEmail(eMail);
				logueado.setContraseña(contraseña);
				logueado.setId(id);
				logueado.setApellido(apellido);
				logueado.setNombre(nombre);
				roles.add(idRol);
				logueado.setIdRol(roles);
				
			}
			logueado.setIdRol(roles);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logueado;
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> ll = new LinkedList<Usuario>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT * FROM Usuarios WHERE idEstadoUsuario = 2");
	
			while (rs.next()) {
			  
				String eMail = rs.getString("eMail");
				String contraseña = rs.getString("contraseña");
				Integer id = rs.getInt("id");
				String apellido = rs.getString("apellido");
				String nombre = rs.getString("nombre");
			  
				Usuario usuario = new Usuario();
				usuario.setEmail(eMail);
				usuario.setContraseña(contraseña);
				usuario.setId(id);
				usuario.setApellido(apellido);
				usuario.setNombre(nombre);
	
				ll.add(usuario);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	@Override
	public void save(Usuario usuario) {

		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			//OBTENGO EL ID DEL ULTIMO USUARIO
			ResultSet rs = query.executeQuery("select id from usuarios order by id desc limit 1"); 
			
			while(rs.next()){
				//AL OBTENER EL ID LE SUMO 1 YA QUE DEBE SER EL PROXIMO USUARIO
				usuario.setId(rs.getInt("id") + 1);
			}
			//SE GUARDA EL USUARIO
			query.executeUpdate("INSERT INTO Usuarios VALUES(" + usuario.getId() + ", '" + usuario.getEmail() + "', '" + usuario.getContraseña() + "', '" + usuario.getApellido()+ "', '" + usuario.getNombre() + "', 1);");
						
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public Map<Integer,String> getRoles(){
		
		Map<Integer,String> roles = new HashMap<Integer, String>();;
		
		try{
			conn =(dataSource.dataSource().getConnection());
			
			Statement query;
			query= conn.createStatement();
			ResultSet rs = query.executeQuery("SELECT * FROM Roles");
			
			while(rs.next()){
				
				roles.put(rs.getInt("id"),rs.getString("descripcion"));
			}
			conn.close();
			
		}catch(SQLException e){
			e.getErrorCode();
			e.getMessage();
		}
		
		return roles;
		
	}
	
	@Override
	public List<Usuario> findPend() {
		List<Usuario> ll = new LinkedList<Usuario>();
		
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT * FROM Usuarios WHERE idEstadoUsuario = 1");
	
			while (rs.next()) {
			  
				String eMail = rs.getString("eMail");
				String contraseña = rs.getString("contraseña");
				Integer id = rs.getInt("id");
				String apellido = rs.getString("apellido");
				String nombre = rs.getString("nombre");
			  
				Usuario usuario = new Usuario();
				usuario.setEmail(eMail);
				usuario.setContraseña(contraseña);
				usuario.setId(id);
				usuario.setApellido(apellido);
				usuario.setNombre(nombre);
	
				ll.add(usuario);
			}
			
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ll;
	}
	
	@Override
	public void updateEstado(Integer id,Integer cdEstado) {

		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			String sql = "UPDATE Usuarios SET idEstadoUsuario = " + cdEstado + " WHERE id = "+ id;
			query = conn.createStatement();		
			query.executeUpdate(sql);
						
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public Usuario findById(Integer idUsuario){
		Usuario usuario = null;
		List<Integer> roles = new ArrayList<Integer>() ;
		try{
			conn = (dataSource.dataSource()).getConnection();
			Statement query = conn.createStatement();
			
			String sql = "SELECT * FROM Usuarios U "
					+ "INNER JOIN ROLESUSUARIOS RU " 
					+ " ON  U.ID = RU.IDUSUARIO "
					+ " WHERE id = "+ idUsuario;
			ResultSet rs = query.executeQuery(sql);
			while(rs.next()){
				String eMail = rs.getString("eMail");
				String contraseña = rs.getString("contraseña");
				Integer id = rs.getInt("id");
				String apellido = rs.getString("apellido");
				String nombre = rs.getString("nombre");
				Integer idRol = rs.getInt("idRol");
				
				usuario = new Usuario();
				usuario.setEmail(eMail);
				usuario.setContraseña(contraseña);
				usuario.setId(id);
				usuario.setApellido(apellido);
				usuario.setNombre(nombre);
				roles.add(idRol);
				usuario.setIdRol(roles);
				
			}
			usuario.setIdRol(roles);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
}
