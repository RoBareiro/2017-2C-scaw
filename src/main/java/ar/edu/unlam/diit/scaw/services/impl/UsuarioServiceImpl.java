package ar.edu.unlam.diit.scaw.services.impl;

import java.util.List;
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

	public UsuarioDaoImpl getUsuarioHsql() {
		return usuarioHsql;
	}

	public void setUsuarioHsql(UsuarioDaoImpl personHsql) {
		this.usuarioHsql = personHsql;
	}

	@Override
	public void save(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

/***************************************************************************************************************/	
	@Override
	public Usuario registroServicio(Usuario usuario){
		return usuarioHsql.registrarDao(usuario);
	}
/****************************************************************************************************************/
	
}
