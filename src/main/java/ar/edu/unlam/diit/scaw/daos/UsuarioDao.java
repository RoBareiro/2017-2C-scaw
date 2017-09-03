package ar.edu.unlam.diit.scaw.daos;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	public List<Usuario> findAll();
	public Usuario login(Usuario usuario);
	public void save(Usuario usuario);

}
