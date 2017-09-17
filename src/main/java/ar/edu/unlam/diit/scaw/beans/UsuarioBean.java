package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import ar.edu.unlam.diit.scaw.services.impl.UsuarioServiceImpl;

@ManagedBean(name = "usuarioBean", eager = true)
@RequestScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String eMail = null;
	private String contraseña = null;
	private Integer id = null;
	private String apellido = null;
	private String nombre = null;
	private Integer idRol = null;
	private Map<Integer,String> roles  = null;
	
	@ManagedProperty("#{param.opc}")
	private String opc = null;
	
	@ManagedProperty("#{param.idUsuario}")
	private Integer idUsuario = null;
	
	MateriaService matService;
	UsuarioService service;
	
	public UsuarioBean() {
		super();
		service = (UsuarioService) new UsuarioServiceImpl();
	}
	
	public UsuarioBean(String eMail, String contraseña, Integer id, String apellido, String nombre) {
		super();
		this.eMail = eMail;
		this.contraseña = contraseña;
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
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
	
	public List<Usuario> getFindPend() {
		List<Usuario> list = service.findPend();
		return list;
	}
	
	public Usuario getFindById(){
		return service.findById(idUsuario);
	}
	
	public Map<Integer,String>getFindAllRoles(){
		
		return service.findAllRoles();
	}
	
	public String login(){
		
		Usuario usuario = new Usuario();
		usuario.setEmail(this.eMail);
		usuario.setContraseña(this.contraseña);		
		
		Usuario logueado = service.login(usuario);		
		if(logueado!=null) 
		{
			if(logueado.getIdRol().size() > 1){
			
				return "multiRol";
				
			}else if(logueado.getIdRol().contains(1)){
				return "admin";
				
			}
			
			return "welcome";			
		}
		else
		{
			return "index";
		}		
	}	
	
	public String registro(){
		
		String mensaje = "Hola";
		return mensaje;
	}
	
	public String admin(){
		
		return "admin";
	}
	
	public String solicitudes(){
		
		service.actualizarEstado((idUsuario), Integer.parseInt(opc));
		return "admin";
	}
	
	public String consultarUsuario(){
			
		return "consultarUsuario";
	}
	
	public String editarUsuario(){
		
		return "editarUsuario";
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

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public MateriaService getMatService() {
		return matService;
	}

	public void setMatService(MateriaService matService) {
		this.matService = matService;
	}

	public Map<Integer, String> getRoles() {
		return roles;
	}

	public void setRoles(Map<Integer, String> roles) {
		this.roles = roles;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getOpc() {
		return opc;
	}

	public void setOpc(String opc) {
		this.opc = opc;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	

}
