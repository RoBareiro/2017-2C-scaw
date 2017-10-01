package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.diit.scaw.entities.Rol;
import ar.edu.unlam.diit.scaw.entities.Usuario;
import ar.edu.unlam.diit.scaw.services.MateriaService;
import ar.edu.unlam.diit.scaw.services.UsuarioService;
import ar.edu.unlam.diit.scaw.services.impl.UsuarioServiceImpl;

@ManagedBean(name = "usuarioBean", eager = true)
@RequestScoped
@SessionScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String eMail = null;
	private String contraseña = null;
	private Integer id = null;
	private String apellido = null;
	private String nombre = null;
	private Integer idRol = null;
	private String error = null;
	private String grantAdmin = "N";
	private String grantDoc = "N";
	private String grantAlumn = "N";
	private String grantAll = "N";
	private Integer idUser = null;
	
	private FacesContext context = FacesContext.getCurrentInstance();
	HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
	
	@SuppressWarnings("unused")
	private List<Rol> roles  = null;
	private List<Usuario> profesores = null;

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
		
		service.save(person, this.idRol);
		
		return "index";
	}
	
	public List<Usuario> getFindAll() {
		List<Usuario> list = service.findAll();
		return list;
	}
	
	public void checkGrandUser( Integer idUsuario){
		
		if(service.isGrantAdm(idUsuario)){
			session.setAttribute("adm","S");
		}
		
		if(service.isGrantAll(idUsuario)){
			session.setAttribute("all","S");
		}
		
		if(service.isGrantAlu(idUsuario)){
			session.setAttribute("alu","S");
		}
		
		if(service.isGrantDoc(idUsuario)){
			session.setAttribute("doc","S");
		}
		
	}
	
	public List<Usuario> getFindPend() {
		List<Usuario> list = service.findPend();
		return list;
	}
	
	/*public Usuario getFindById(){
		return service.findById(idUsuario);
	}*/
	
	
	public String login(){
		
		Usuario usuario = new Usuario();
		usuario.setEmail(this.eMail);
		usuario.setContraseña(this.contraseña);
		Usuario logueado = service.login(usuario);		
		if(logueado!=null) {

			checkGrandUser(logueado.getId());
			return "welcome";
	}
		return "index";
}

	
	public String registro(){
		
		return "registro";
	}
	
	public String admin(){
		
			return "admin";	

		
	}
	
	public String solicitudesUsuarios(){
	
		return "solicitudesUsuarios";	
	
	}
	
	public String gestionMaterias(){
		
			return "gestionMaterias";	
		
	}
	
public String gestionExamenes(){
		
			return "gestionExamenes";	
		
	}

public String nuevaMateria(){
	
		 
		
		return "nuevaMateria";
	
	
	
		
}

public String usuariosActivos(){
			
		return "usuariosActivos";

	
		
}

public String nuevoExamen(){
	
		
		return "formularioExamenes";
	
}

	
	public String solicitudes(){
		
		//service.actualizarEstado((idUsuario), Integer.parseInt(opc));
		return "welcome";
	
}
	
	public String consultarUsuario(){
			
		return "consultarUsuario";
	}
	
	public String editarUsuario(){
		
			Integer idUsuarioEdit = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuarioEdit"));
			Usuario user = service.findById(idUsuarioEdit);
			eMail = user.getEmail();
			contraseña = user.getContraseña();
			id = user.getId();
			apellido = user.getApellido();
			nombre = user.getNombre();
			return "editarUsuario";

	}
	
	public String actualizarUsuario(){
		try{
			service.actualizarUsuario(this.id, this.eMail,this.contraseña, this.apellido, this.nombre);
		}catch(Exception e){
			System.out.print("Ha ocurrido un error: "+ e.getMessage());
		}
		return "welcome";
		
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

	public List<Rol> getRoles() {
		return service.getRoles();
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}




	public List<Usuario> getProfesores() {
		return service.getAllProfesores();
	}

	public void setProfesores(List<Usuario> profesores) {
		this.profesores = profesores;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getGrantAdmin() {
		return grantAdmin;
	}

	public void setGrantAdmin(String grantAdmin) {
		this.grantAdmin = grantAdmin;
	}

	public String getGrantDoc() {
		return grantDoc;
	}

	public void setGrantDoc(String grantDoc) {
		this.grantDoc = grantDoc;
	}

	public String getGrantAlumn() {
		return grantAlumn;
	}

	public void setGrantAlumn(String grantAlumn) {
		this.grantAlumn = grantAlumn;
	}

	public String getGrantAll() {
		return grantAll;
	}

	public void setGrantAll(String grantAll) {
		this.grantAll = grantAll;
	}


	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}


	
	

}
