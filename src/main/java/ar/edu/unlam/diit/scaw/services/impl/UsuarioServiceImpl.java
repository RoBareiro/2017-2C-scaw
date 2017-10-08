package ar.edu.unlam.diit.scaw.services.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import ar.edu.unlam.diit.scaw.daos.impl.UsuarioDaoImpl;
import ar.edu.unlam.diit.scaw.entities.Rol;
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
	public void save(Usuario usuario,Integer idRol) {

		this.usuarioHsql.save(usuario,idRol);
		
	}
	
	@Override
	public List<Rol> getRoles(){
		
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

	@Override
	public List<Usuario> getAllProfesores() {
		
		return usuarioHsql.getAllProfesores();
	}
	
	@Override
	public void actualizarUsuario(Integer id,String mail, String contraseña,String apellido,String nombre){
		
		usuarioHsql.updateUsuario(id, mail, contraseña, apellido, nombre);
	}

	@Override
	public boolean isGrantAll(Integer id){
		
		Usuario user = usuarioHsql.findById(id);
		
		if(user.getIdRol().size() == 3){
			return true;
		}
		
		return false;
		
	}
	@Override
	public boolean isGrantDoc(Integer id){
		Usuario user = usuarioHsql.findById(id);
		
		if(user.getIdRol().contains(2)){
			return true;
		}
		
		return false;		
	}
	
	@Override
	public boolean isGrantAlu(Integer id){
		Usuario user = usuarioHsql.findById(id);
			
		if(user.getIdRol().contains(3)){
			return true;
		}
		
		return false;		
		
		
	}
	@Override
	public boolean isGrantAdm(Integer id){
		Usuario user = usuarioHsql.findById(id);
		
		if(user.getIdRol().contains(1)){
			return true;
		}
		
		return false;		
		
		
	}

	@Override
	public String guardarPass(String pass){
		

		String secretKey = "SCAW";
		String passBase64 = "";
        try {
 
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
 
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
 
            byte[] plainTextBytes = pass.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            passBase64 = new String(base64Bytes);
 
        } catch (Exception ex) {
        }
        return passBase64;
	}
	
	public String recuperarPass(String pass){
		String secretKey = "SCAW";
		String passBase64 = "";
		try{
			
			byte[]message = Base64.decodeBase64(pass.getBytes("UTF-8"));
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("UTF-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			 
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            
            byte[] plainText = decipher.doFinal(message);
            
            passBase64 = new String(plainText, "UTF-8");

        } catch (Exception ex) {
        }
        return passBase64;
	}
	
	public boolean isValidPass(String pass1,String pass2){
		
		if(pass1.equals(pass2)){
			return true;
		}else{
			return false;
		}
	}
	
}

