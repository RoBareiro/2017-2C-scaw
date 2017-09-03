package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import ar.edu.unlam.diit.scaw.services.impl.UsuarioServiceImpl;

@ManagedBean(name = "usuarioBean", eager = true)
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id = null;
	private String eMail = null;
	private String contraseña = null;
	private String apellido = null;
	private String nombre = null;
	private Integer idEstadoUsuario = null;
	
	
	UsuarioService service;
	
	public UsuarioBean() {
		super();
		service = (UsuarioService) new UsuarioServiceImpl();
	}
	
	public UsuarioBean(String eMail, String contraseña, Integer id, String apellido, String nombre, Integer idEstadoUsuario) {
		super();
		this.eMail = eMail;
		this.contraseña = contraseña;
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.idEstadoUsuario = idEstadoUsuario;
	}
	
	public String save() {
		
		Usuario person = buildUsuario();
		
		service.save(person);
		
		return "welcome";
	}
	
	public List<Usuario> getFindAll() {
		List<Usuario> list = service.findAll();
		return list;
	}
	
	public String login(){
		
		Usuario usuario = new Usuario();
		usuario.setEmail(this.eMail);
		usuario.setContraseña(this.contraseña);		
		
		Usuario logueado = service.login(usuario);		
		if(logueado!=null) 
		{
			return "welcome";			
		}
		else
		{
			return "index";
		}		
	}	

	private Usuario buildUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setEmail(this.eMail);
		usuario.setContraseña(contraseña);
		usuario.setId(id);
		usuario.setApellido(this.apellido);
		usuario.setNombre(this.nombre);
		
		return usuario;
	}
	
/**************************************************************************************************************/

	public String registro(){
		
		Usuario usuarioRegistro = new Usuario();
		
		usuarioRegistro.setEmail(this.eMail);
		usuarioRegistro.setContraseña(this.contraseña);
		usuarioRegistro.setApellido(this.apellido);
		usuarioRegistro.setNombre(this.nombre);
		usuarioRegistro.setIdEstadoUsuario(this.idEstadoUsuario);
		
		Usuario registrado = service.registroServicio(usuarioRegistro);		
		if(registrado == null) 
		{/*Si retorna null es porque no existe en la bd, entonces se podria registrar con exito*/
			return "registroCorrecto";			
		}
		else
		{
			return "registroUsuario";
		}		
	}	
	
	
/*************************************************************************************************************/	

	public String getEmail() {
		return eMail;
	}

	public void setEmail(String email) {
		this.eMail = email;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public UsuarioService getService() {
		return service;
	}

	public void setService(UsuarioService service) {
		this.service = service;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
/**************************************************************************************************************/	
	
	public Integer getIdEstadoUsuario() {
		return idEstadoUsuario;
	}

	public void setIdEstadoUsuario(Integer idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
	}

/***************************************************************************************************************/
}
