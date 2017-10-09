package ar.edu.unlam.diit.scaw.beans;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
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
	private String clave = null;
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
	private String tipoAccion = null;
	
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
	
	public UsuarioBean(String eMail, String clave, Integer id, String apellido, String nombre) {
		super();
		this.eMail = eMail;
		this.clave = clave;
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
	}
	
	public String save() throws Exception {
		
		Usuario person = buildUsuario();
			
		person.setClave(service.encriptar(this.clave));
		
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
		
		session.setAttribute("nomUsu", service.findById(idUsuario).getNombre());
		
		session.setAttribute("id",idUsuario);
		
	}
	
	public List<Usuario> getFindPend() {
		List<Usuario> list = service.findPend();
		return list;
	}
	
	public String login() throws Exception{
		
		Usuario usuario = new Usuario();
		usuario.setEmail(this.eMail);
		Usuario logueado = service.login(usuario);
		if(service.isValidPass(this.clave,logueado.getClave())) {

			checkGrandUser(logueado.getId());
			
			session.setAttribute("logeado","Y");
			
			return "welcome";
	}
		return "index";
}
	
	public String registro(){
		tipoAccion = "RE";
		return "registro";
	}
	
	public String solicitudesUsuarios(){
	
		String id = session.getAttribute("id").toString();
		Integer idUsuario = Integer.parseInt(id);
		
		if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario)){
			return "solicitudesUsuarios";	
		}
		
		error = "No tienes permisos/privilegios para realizar la accion deseada";
		return "welcome";
	}
	
	public String gestionMaterias(){
		String id = session.getAttribute("id").toString();
		String logeado = session.getAttribute("logeado").toString();
		Integer idUsuario = Integer.parseInt(id);
		
		if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario) && logeado.equals("Y")){
		
			return "gestionMaterias";	
		}
		
		error = "No tienes permisos/privilegios para realizar la accion deseada";
		return "welcome";
		
	}
	
public String gestionExamenes(){
	String id = session.getAttribute("id").toString();
	String logeado = session.getAttribute("logeado").toString();
	Integer idUsuario = Integer.parseInt(id);
	
	if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario) && logeado.equals("Y")){
			return "gestionExamenes";	
	}
	error = "No tienes permisos/privilegios para realizar la accion deseada";
	return "welcome";
		
	}

public String nuevaMateria(){
	
	String id = session.getAttribute("id").toString();
	String logeado = session.getAttribute("logeado").toString();
	Integer idUsuario = Integer.parseInt(id);	 
	
	if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario) && logeado.equals("Y")){
		return "nuevaMateria";
	}
	
	error = "No tienes permisos/privilegios para realizar la accion deseada";
	return "welcome";
			
}

public String usuariosActivos(){
	String id = session.getAttribute("id").toString();
	String logeado = session.getAttribute("logeado").toString();
	Integer idUsuario = Integer.parseInt(id);	 
	
	if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario) && logeado.equals("Y")){		
		return "usuariosActivos";
	}
		error = "No tienes permisos/privilegios para realizar la accion deseada";
		return "welcome";	
}

public String nuevoExamen(){
	
	String id = session.getAttribute("id").toString();
	String logeado = session.getAttribute("logeado").toString();
	Integer idUsuario = Integer.parseInt(id);	 
	
	if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario) && logeado.equals("Y")){	
		return "formularioExamenes";
	}
	error = "No tienes permisos/privilegios para realizar la accion deseada";
	return "welcome";	
	
}

	
	public String solicitudes(){
		
		String error;
		String  opcion = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("opc");
		String  Usuario = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuario");
		
		String id = session.getAttribute("id").toString();
		String logeado = session.getAttribute("logeado").toString();
		Integer idLogueado = Integer.parseInt(id);
		Integer opc = Integer.parseInt(opcion);
		Integer idUsuario = Integer.parseInt(Usuario);
		List<Usuario> userspend = service.findPend();
		Usuario useramodif = service.findById(idUsuario);
		
				System.out.println("**************************************USER: " + useramodif.toString());

				System.out.println("**************************************LISTAUSERS: " + userspend.toString());

				
		if(service.isGrantAdm(idLogueado) || service.isGrantAll(idLogueado) && logeado.equals("Y")){
			//Chequea que no haya solicitudes pendientes
			if(userspend.contains(useramodif)){

				if(opc == 2 || opc == 3){
					
					service.actualizarEstado(idUsuario, opc);
					
					return "solicitudesUsuarios";
				
				} else {
					
					error = "Opción elegida no esta disponible";
				}
			} else {
				
				error = "No tienes solicitudes pendientes";
			}
		}
		
		error = "No tienes permisos/privilegios para realizar la accion deseada";
		return "welcome";
	
}
	
	public String consultarUsuario(){
		
		Integer idUsuarioConsul = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuarioConsul"));
		Usuario user = service.findById(idUsuarioConsul);
		
		String idS = session.getAttribute("id").toString();
		String logeado = session.getAttribute("logeado").toString();
		Integer idUsuario = Integer.parseInt(idS);
		
		try{
			if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario) && logeado.equals("Y")){
			tipoAccion = "CO";
			eMail = user.getEmail();
			clave = user.getClave();
			id = user.getId();
			apellido = user.getApellido();
			nombre = user.getNombre();
			return "consultarUsuario";
			}
		}catch(Exception e){
			System.out.println("Se ha producido un error: " + e.getMessage());
			return "consultarUsuario";
		}
		return "welcome";
	}
	
	public String editarUsuario(){
		
			Integer idUsuarioEdit = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idUsuarioEdit"));
			Usuario user = service.findById(idUsuarioEdit);
			
			String idS = session.getAttribute("id").toString();
			String logeado = session.getAttribute("logeado").toString();
			Integer idUsuario = Integer.parseInt(idS);
			
			tipoAccion = "ED";
			try{
				if(service.isGrantAdm(idUsuario) || service.isGrantAll(idUsuario) && logeado.equals("Y")){
					eMail = user.getEmail();
					clave = user.getClave();
					id = user.getId();
					apellido = user.getApellido();
					nombre = user.getNombre();
					return "editarUsuario";
				}
			}catch(Exception e){
				System.out.println("Se ha producido un error: " + e.getLocalizedMessage());
				return "editarUsuario";
			}
			
			return "welcome";
	}
	
	public String actualizarUsuario(){
		try{
			if(!validarNombre(this.nombre)){
				error = "EL nombre ingresado es invalido, debe contener solo letras";
				return "editarUsuario";
			}else if(!validarApellido(this.apellido)){
				error = "EL apellido ingresado es invalido, debe contener solo letras";
				return "editarUsuario";
			}else if(!validarEmail(this.eMail)){
				error = "EL mail ingresado es invalido, introduce un mail valido";
				return "editarUsuario";
			}else{
				String passEncript = service.encriptar(this.clave);
				service.actualizarUsuario(this.id, this.eMail,passEncript, this.apellido, this.nombre);
			}
			
		}catch(Exception e){
			System.out.print("Ha ocurrido un error: "+ e.getMessage());
		}
		return "welcome";
		
	}
	

	public  boolean validarNombre(String nombre){
		
		Pattern patNom = Pattern.compile("[a-zA-Z]");
		Matcher mather = patNom.matcher(nombre);
		if(!mather.find()){
			return false;
		}
		
		return true;
	}
	
	
	public  boolean validarApellido(String Apellido){
		Pattern patApe = Pattern.compile("[a-zA-Z]");
		Matcher mather = patApe.matcher(apellido);
		if(!mather.find()){
			return false;
		}
		return true;
		
	}
	
	public boolean validarEmail(String eMail){
		Pattern patMail = Pattern.
				compile("[A-Za-z]+@[a-z]+\\.[a-z]+");
		
		Matcher mather = patMail.matcher(eMail);
		
		if(!mather.find()){
			return false;
		}
		
		return true;
	}
	

	private Usuario buildUsuario() {
		Usuario usuario = new Usuario();
		
		usuario.setEmail(this.eMail);
		usuario.setClave(clave);
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

	public String getClave() {
		return clave;

	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public String getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}



}
