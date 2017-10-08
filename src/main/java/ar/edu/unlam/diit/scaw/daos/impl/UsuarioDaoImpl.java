package ar.edu.unlam.diit.scaw.daos.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.Statement;

import ar.edu.unlam.diit.scaw.configs.HsqlDataSource;
import ar.edu.unlam.diit.scaw.daos.UsuarioDao;
import ar.edu.unlam.diit.scaw.entities.Rol;
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
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Usuarios u "
					+ " inner join rolesusuarios ru "
					+ " on u.id = ru.idusuario "
					+ " where eMail = ? and idEstadoUsuario = 2 ");
				//+ " where eMail = ? and contraseña = ? and idEstadoUsuario = 2 ");
			ps.setString(1, usuario.getEmail());
			//ps.setString(2,usuario.getContraseña());
			
			
			ResultSet rs = ps.executeQuery();
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
	public void save(Usuario usuario, Integer idRol) {

		try {
			Integer lastid = null;
			
			conn = (dataSource.dataSource()).getConnection();
	        Statement stmt = conn.createStatement();
	        
	      //OBTENGO EL ID DEL ULTIMO USUARIO
			ResultSet rs = stmt.executeQuery("select id from usuarios order by id desc limit 1"); 
			
			while(rs.next()){
				//AL OBTENER EL ID LE SUMO 1 YA QUE DEBE SER EL PROXIMO USUARIO
				lastid = rs.getInt("id") + 1;
				usuario.setId(lastid);
			}
	        
	        PreparedStatement ps = conn.prepareStatement("INSERT INTO Usuarios(Id,Email,Contraseña,Apellido,Nombre,idEstadoUsuario)"
	        		+ "  VALUES(?,?,?,?,?,1)");
	        ps.setInt(1, usuario.getId());
			ps.setString(2,usuario.getEmail());			
			ps.setString(3,usuario.getContraseña());
			ps.setString(4,usuario.getApellido());
			ps.setString(5,usuario.getNombre());
			
			ps.execute();
			
			
			if(lastid != 0){				
				//SE GUARDA LA RELACION ENTRE EL USUARIO Y EL ROL
				PreparedStatement ps1 = conn.prepareStatement("Insert into rolesusuarios values(?,?)");
				String queryrol = "Insert into rolesusuarios values(" + lastid + "," + idRol + ");"; 

				//SE EJECUTA DICHA QUERY
				stmt.executeUpdate(queryrol);
				//CIERRO LA CONEXION
				conn.close();
			} else {
				
				throw new SQLException("Fallo al insertar RolesUsuarios");
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public List<Rol> getRoles(){
		
		List<Rol> roles = new ArrayList<Rol>();
		
		try{
			conn =(dataSource.dataSource().getConnection());
			
			Statement query;
			query= conn.createStatement();
			ResultSet rs = query.executeQuery("SELECT * FROM Roles");
			
			while(rs.next()){
				Rol rol = new Rol();
				rol.setId(rs.getInt("id"));
				rol.setDescripcion(rs.getString("descripcion"));
				roles.add(rol);
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
		List<Integer> roles = new ArrayList<Integer>();
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			Statement query;
			
			query = conn.createStatement();
			
			ResultSet rs = query.executeQuery("SELECT * FROM Usuarios as u"
					+ " join rolesusuarios as ru on u.id = ru.idusuario"
					+ " WHERE idEstadoUsuario = 1");
	
			while (rs.next()) {
			  
				String eMail = rs.getString("eMail");
				String contraseña = rs.getString("contraseña");
				Integer id = rs.getInt("id");
				String apellido = rs.getString("apellido");
				String nombre = rs.getString("nombre");
				Integer idrol = rs.getInt("idrol");
				roles.add((idrol));
				
				
				Usuario usuario = new Usuario();
				usuario.setEmail(eMail);
				usuario.setContraseña(contraseña);
				usuario.setId(id);
				usuario.setApellido(apellido);
				usuario.setNombre(nombre);
				usuario.setIdRol(roles);
	
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
		
			PreparedStatement ps = conn.prepareStatement("UPDATE Usuarios SET idEstadoUsuario = ? "
					+ " WHERE id = ?");
			
			ps.setInt(1,cdEstado);
			ps.setInt(2, id);
					
			ps.executeUpdate();
						
			ps.close();
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
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM usuarios U "
					+ " INNER JOIN ROLESUSUARIOS RU "
					+ " ON U.ID = RU.IDUSUARIO "
					+ " WHERE ID = ?");
			ps.setInt(1,idUsuario);
			
			ResultSet rs = ps.executeQuery();
			
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
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public List<Usuario> getAllProfesores() {
	
		List<Usuario> profs = new ArrayList<Usuario>();
		
		try{
			conn = (dataSource.dataSource()).getConnection();
			Statement stmt = conn.createStatement();
		
			String query = "select u.id as id, u.nombre as nombre, u.apellido as apellido from usuarios as u join rolesusuarios as ru on u.id = ru.idusuario where ru.idrol = 2;";
			ResultSet rs = stmt.executeQuery(query);
			
			
			while(rs.next()){
				
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido(rs.getString("apellido"));
			
				System.out.println("id:" + usuario.getId() + "//Nombre: " + usuario.getNombre() + " /AP: " + usuario.getApellido());
				profs.add(usuario);
			
			}
			conn.close();
			return profs;
			
		}catch(Exception e){

			e.printStackTrace();			
		}
		return profs;
	}
	
	@Override
	public void updateUsuario(Integer id,String mail, String contraseña,String apellido,String nombre){
		try {
			conn = (dataSource.dataSource()).getConnection();
		
			PreparedStatement ps = conn.prepareStatement("UPDATE Usuarios Set"
					+ " eMail = ? ,"
					+ " contraseña = ? , "
					+ " apellido = ? , "
					+ " nombre = ?   "
					+ " where id = ? ");
			ps.setString(1,mail);
			ps.setString(2,contraseña);
			ps.setString(3,apellido);
			ps.setString(4,nombre);
			ps.setInt(5, id);
			
			/*Statement query;
			
			String sql = "UPDATE Usuarios SET "
					+ " eMail = '" 		+ mail + "', " 
					+ " contraseña = '"  +  contraseña + "', " 
					+ " apellido = '" 	+ 	apellido + "', "
            		+ "	nombre = '"   	+	nombre + "'" 
					+ " WHERE id = " 	+ id;
			 	 	

			query = conn.createStatement();	*/	
			ps.executeUpdate();
						
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
