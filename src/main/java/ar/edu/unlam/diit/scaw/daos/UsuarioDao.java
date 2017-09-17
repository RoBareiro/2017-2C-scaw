package ar.edu.unlam.diit.scaw.daos;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioDao {

	public List<Usuario> findAll();
	public Usuario login(Usuario usuario);
	public void save(Usuario usuario);
	public Map<Integer,String>getRoles();
	public List<Usuario> findPend();
	public Usuario findById(Integer idUsuario);
	public void updateEstado(Integer id,Integer cdEstado);
	
}
