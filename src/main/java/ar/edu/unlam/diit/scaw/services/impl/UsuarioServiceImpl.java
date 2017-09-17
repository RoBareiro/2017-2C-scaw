package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
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
	public void save(Usuario usuario) {

		this.usuarioHsql.save(usuario);
		
	}
	
	@Override
	public Map<Integer,String>findAllRoles(){
		
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
}

