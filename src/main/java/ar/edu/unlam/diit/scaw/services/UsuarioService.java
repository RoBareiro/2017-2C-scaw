package ar.edu.unlam.diit.scaw.services;

import java.util.List;
import java.util.Map;

import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {

	public Usuario login(Usuario usuario);
	public List<Usuario> findAll();
	public List<Usuario> findPend();
	public Usuario findById(Integer idUsuario);
	public void save(Usuario usuario);
	public Map<Integer,String>findAllRoles();
	public void actualizarEstado(Integer id,Integer cdEstado);


}
