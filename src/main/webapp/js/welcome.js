$(document).ready(function(){
	
//	$('#myForm\\:administrar').addClass('ocultar');
	$('#admLi').addClass('ocultar');
	
	$('#docLi').addClass('ocultar');
	//$('#myForm\\:docente').addClass('ocultar');
	
	$('#aluLI').addClass('ocultar');
	//$('#myForm\\:alumno').addClass('ocultar');
	
	
	verificarPermisos();
	
});

function verificarPermisos(){
	
var idUsuario = $('#myForm\\:id').val();
var grantAll =$('#myForm\\:grantAll').val();
var grantAdm =$('#myForm\\:grantAdm').val();
var grantAlu =$('#myForm\\:grantAlu').val();
var grantDoc =$('#myForm\\:grantDoc').val();

	if( grantAll === 'S'){
		$('#aluLI').removeClass('ocultar');
		$('#admLi').removeClass('ocultar');
		$('#docLi').removeClass('ocultar');
	}
	if(grantDoc === 'S'){
		$('#docLi').removeClass('ocultar');
	}
	if(grantAdm === 'S'){
		$('#admLi').removeClass('ocultar');
	}
	if(grantAlu === 'S'){
		$('#aluLI').removeClass('ocultar');	
	}
	
}