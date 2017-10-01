package ar.edu.unlam.diit.scaw.services;

import java.util.List;

import ar.edu.unlam.diit.scaw.entities.Rol;
import ar.edu.unlam.diit.scaw.entities.Usuario;

public interface UsuarioService {

	public Usuario login(Usuario usuario);
	public List<Usuario> findAll();
	public List<Usuario> findPend();
	public Usuario findById(Integer idUsuario);
	public void save(Usuario usuario, Integer idRol);
	public List<Rol> getRoles();
	public void actualizarEstado(Integer id,Integer cdEstado);
	public List<Usuario> getAllProfesores();
	public void actualizarUsuario(Integer id,String mail, String contraseña,String apellido,String nombre);
	public boolean isGrantAll(Integer id);
	public boolean isGrantDoc(Integer id);
	public boolean isGrantAlu(Integer id);
	public boolean isGrantAdm(Integer id);
	


}
