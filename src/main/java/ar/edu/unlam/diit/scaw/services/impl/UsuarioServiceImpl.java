package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Rol;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService {

	UsuarioDaoImpl usuarioHsql;
	
	public UsuarioServiceImpl(){
		usuarioHsql = new UsuarioDaoImpl();
	}
	
	@Override
	public Usuario login(Usuario usuario){
		return usuarioHsql.login(usuario);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioHsql.findAll();
	}
	
	@Override
	public List<Usuario> findPend() {
		return usuarioHsql.findPend	();
	}

	public UsuarioDaoImpl getUsuarioHsql() {
		return usuarioHsql;
	}

	public void setUsuarioHsql(UsuarioDaoImpl personHsql) {
		this.usuarioHsql = personHsql;
	}

	@Override
	public void save(Usuario usuario,Integer idRol) {

		this.usuarioHsql.save(usuario,idRol);
		
	}
	
	@Override
	public List<Rol> getRoles(){
		
		return usuarioHsql.getRoles();
		
	}
	
	@Override
	public void actualizarEstado(Integer id, Integer cdEstado){
		usuarioHsql.updateEstado(id, cdEstado);
	}
	
	@Override
	public Usuario findById(Integer idUsuario){
		
		return usuarioHsql.findById(idUsuario);
	}

	@Override
	public List<Usuario> getAllProfesores() {
		
		return usuarioHsql.getAllProfesores();
	}
	
	@Override
	public void actualizarUsuario(Integer id,String mail, String contraseña,String apellido,String nombre){
		
		usuarioHsql.updateUsuario(id, mail, contraseña, apellido, nombre);
	}

	@Override
	public boolean isGrantAll(Integer id){
		
		Usuario user = usuarioHsql.findById(id);
		
		if(user.getIdRol().size() == 3){
			return true;
		}
		
		return false;
		
	}
	@Override
	public boolean isGrantDoc(Integer id){
		Usuario user = usuarioHsql.findById(id);
		
		if(user.getIdRol().contains(2)){
			return true;
		}
		
		return false;		
	}
	
	@Override
	public boolean isGrantAlu(Integer id){
		Usuario user = usuarioHsql.findById(id);
			
		if(user.getIdRol().contains(3)){
			return true;
		}
		
		return false;		
		
		
	}
	@Override
	public boolean isGrantAdm(Integer id){
		Usuario user = usuarioHsql.findById(id);
		
		if(user.getIdRol().contains(1)){
			return true;
		}
		
		return false;		
		
		
	}
}

