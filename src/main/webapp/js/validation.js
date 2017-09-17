/**
 * 
 */
$(document).ready(function(){

	$('#login\\:eMail').removeClass('error');
	$('#login\\:contraseña').removeClass('error');
	
	$('#login\\:eMail').on('blur',validarEmail);
	$('#login\\:contraseña').on('blur',validarPass);
	
	function validarEmail(){
		
		if( $('#login\\:eMail').val() == ""){
			$('#login\\:eMail').addClass('error');
			return false;
		}
		
		return true;
	}

function validarPass(){

	if($('#login\\:contraseña').val() == ""){
		$('#login\\:contraseña').addClass('error');
		return false;
	}
	return true;
}

	
$('form').submit(function(e){
	e.preventDefault();
	
	if(!validarEmail){
		alert("El Campo Mail Esta vacio");
	}else if(!validarPass){
		alert("No haz Introduciod la Contraseña");
	}else $(this).off('submit').submit();
	
})		

	
	
})
	

