<!DOCTYPE html>
<html lang="esS" >
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css"/>
<link rel="stylesheet" href="css/bootstrapValidator.css"/>

<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>
<jsp:include page="intranetCabecera.jsp" />
	<div class="container" style="margin-top: 4%">
	<h4>Registra Sala</h4>
	
	<form id="id_form" action=" " >		
		<div class="row" style="margin-top: 4%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_numero">Numero</label>
				<input class="form-control" type="text" id="id_numero" name="numero" placeholder="Ingrese el numero">	
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="id_piso">Piso</label>
				<input class="form-control" type="text" id="id_piso" name="piso" placeholder="Ingrese el piso">	
			</div>
		</div>
		<div class="row" style="margin-top: 2%">
			<div class="form-group col-md-3">
				<label class="control-label" for="id_cantidad">Numero de Alumnos</label>
				<input class="form-control" type="text" id="id_cantidad" name="cantidad" placeholder="Ingrese el numero de alumnos">	
			</div>
			<div class="form-group col-md-3">
				<label class="control-label" for="id_recursos">Recursos</label>
				<input class="form-control" type="text" id="id_recursos" name="recursos" placeholder="Ingrese los recursos">	
			</div>
		</div>
		<div class="row" style="margin-top: 2%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_sede"> Sede </label> 
				<select	class="form-control" id="id_sede" name="sede" >
					<option value=" ">[Seleccione]</option>
				</select>
			</div>
		</div>
		<div class="row"  align="center" style="margin-top: 2%">
			<div class="form-group col-md-12">
					<button type="button" class="btn btn-primary" id="id_btn_registrar">Crea Sala</button>
			</div>
		</div>
	</form>
	
</div>


<script type="text/javascript">

$(document).ready(function(){
	$('#id_form').bootstrapValidator(
                {
                    message : 'This value is not valid',
                    feedbackIcons : {
                        valid : 'glyphicon glyphicon-ok',
                        invalid : 'glyphicon glyphicon-remove',
                        validating : 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                    	numero: {
							selector: '#id_numero',
                            validators: {
                                notEmpty: {
                                    message: 'El numero es requerido'
                                },
                                regexp: {
                                    regexp: /^[A][0-9]{4}$/,
                                    message: 'El numero debe ser numérico que empiece con A'
                                },
                            }
						},
						piso: {
							selector: '#id_piso',
                            validators: {
                                notEmpty: {
                                    message: 'La Cantidad de piso es requerido'
                                },
                                regexp: {
                                    regexp: /^[0-9]{1}$/,
                                    message: 'La Cantidad de piso debe ser numérico de 1 dígitos'
                                },
                            }
						},
						numAlumnos: {
							selector: '#id_cantidad',
                            validators: {
                                notEmpty: {
                                    message: 'La Cantidad de alumnos es requerido'
                                },
                                regexp: {
                                    regexp: /^[0-99]{2}$/,
                                    message: 'La Cantidad de alumnos debe ser numérico de 2 dígitos'
                                },
                            }
						},
						recursos: {
							selector: '#id_recursos',
                            validators: {
                                notEmpty: {
                                    message: 'El recurso es requerido'
                                },
                                stringLength: {
                                    min: 3,
                                    max: 50,
                                    message: 'El recurso debe tener entre 3 y 50 caracteres'
                                }
                            }
						},
						sede: {
							selector: '#id_sede',
                            validators: {
                                notEmpty: {
                                    message: 'La sede es requerido'
                                }
                            }
						}
                    }
                });
});
    
$("#id_btn_registrar").click(function() {
    var validator = $('#id_form').data('bootstrapValidator');
    validator.validate();
    
    if (validator.isValid()) {
    	$.ajax({
            type: "POST",
            url: "registraSala",
            data : $('#id_form').serialize(),
            success: function(data) {
            	mostrarMensaje(data.mensaje);
	            validator.resetForm();
            },
            error: function(data) {
            	mostrarMensaje(MSG_ERROR);
            }
         });
    }
   
});


$.getJSON("cargaComboSede", function(data){
	$.each(data, function(key, value){
        $("#id_sede").append("<option value='"+value.idSede+"'>"+value.nombre+"</option>");
    });
});	

</script>

</body>
</html>