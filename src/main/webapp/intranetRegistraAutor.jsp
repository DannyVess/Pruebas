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
	<h4>Registra Autor</h4>
		
		
		<div class="container">
    <form action="registraAutor" id="form_autor">
        <div class="row" style="margin-top: 5%">
            <!-- Primera columna -->
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="id_nombres" class="control-label">Nombres</label>
                    <input class="form-control" type="text" id="id_nombres" name="nombres"
                           placeholder="Ingrese los nombres" maxlength="30" required>
                </div>
                <div class="form-group">
                    <label for="id_apellidos" class="control-label">Apellidos</label>
                    <input class="form-control" type="text" id="id_apellidos" name="apellidos"
                           placeholder="Ingrese los apellidos" maxlength="30" required>
                </div>
                <div class="form-group">
                    <label for="id_telefono" class="control-label">Teléfono</label>
                    <input class="form-control" type="tel" id="id_telefono" name="telefono"
                           placeholder="Ingrese el teléfono" pattern="\d{9}" maxlength="9" required>
                </div>
                <div class="form-group">
                    <label for="id_grado" class="control-label">Grado</label>
                    <select class="form-control" id="id_grado" name="grado" required>
                        <option value="">[Seleccione]</option>
         
                    </select>
                </div>
            </div>


            <div class="col-sm-6">
                <div class="form-group">
                    <label for="id_fechaNacimiento" class="control-label">Fecha de Nacimiento</label>
                    <input class="form-control" type="date" id="id_fechaNacimiento" name="fechaNacimiento" required>
                </div>
              
                
            </div>
        </div>

        <div class="row" style="margin-top: 2%" align="center">
            <button id="btn_registrar" type="submit" class="btn btn-primary">Crear Autor</button>
        </div>
    </form>
</div>
	
</div>
	

	
	
	
<script  type="text/javascript">

$(document)
.ready(
    function() {
        $('#form_autor')
            .bootstrapValidator(
                {
                    message : 'This value is not valid',
                    feedbackIcons : {
                        valid : 'glyphicon glyphicon-ok',
                        invalid : 'glyphicon glyphicon-remove',
                        validating : 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        nombres: {
                            validators: {
                                notEmpty: {
                                    message: 'Los Nombres son requeridos'
                                },
                            }
                        },
                        apellidos: {
                            validators: {
                                notEmpty: {
                                    message: 'Los Apellidos son requeridos'
                                },
                            }
                        },
                        telefono:{
                            selector: "#id_telefono",
                            validators:{
                                notEmpty: {
                                    message: 'El tel&eacute;fono es obligatorio'
                                },
                                regexp: {
                                    regexp: /^9[0-9]{8}$/,
                                    message: 'El tel&eacute;fono es de 9 d&iacute;gitos y empieza con 9'
                                }
                            }
                        },
                        fechaNacimiento: {
                            selector : "#id_fechaNacimiento",
                            validators : {
                                notEmpty : {
                                    message : 'La fecha de nacimiento es requerida'
                                },
                            }
                        },
                        grado: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe seleccionar un grado'
                                }
                            }
                        }
                    }
                });
    });
    
   
 
$("#btn_registrar").click(function(event) {
	event.preventDefault();
    var validator = $('#form_autor').data('bootstrapValidator');
    validator.validate();

    if (validator.isValid()) {
        $.ajax({
            type: "POST",
            url: "registraAutor",
            data: $('#form_autor').serialize(),
            success: function(data){
              	mostrarMensaje(data.mensaje);
			    limpiarFormulario();
                validator.resetForm();
            },
            error: function(){
            	
              mostrarMensaje(MSG_ERROR);
            }
        });
    }
});


$.getJSON("cargaGrado", function(data){
	$.each(data, function(key, value){
        $("#id_grado").append("<option value='"+value.idGrado+"'>"+value.descripcion+"</option>");
    });
});

function limpiarFormulario() {
    $('#id_nombres').val('');
    $('#id_apellidos').val('');
    $('#id_telefono').val('');
    $('#id_grado').val('');
}
    
</script>
	
	
	
</body>
</html>