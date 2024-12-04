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
	<h4>Registra Tesis</h4>
	
	
	<div class="container">
        <form action=" "  id="id_form">
            <div class="row" style="margin-top: 5%">
                <!-- Primera columna -->
                <div class="col-sm-6">
                
                    <div class="form-group">
                        <label for="id_titul" class="control-label">Titulo</label> <input
                            class="form-control" type="text" id="id_titulo" name="titulo"
                            placeholder="Ingrese el Titulo" maxlength="30">
                    </div>
                    
                    <div class="form-group">
                        <label for="id_tema" class="control-label">Tema</label> <input
                            class="form-control" type="text" id="id_tema" name="tema"
                            placeholder="Ingrese el tema de la Tesis" maxlength="30">
                    </div>
                
                    <div class="form-group">
                        <label for="id_alumno" class="control-label">Alumnno</label>
                        <select class="form-control" id="id_alumno" name="idAlumno">
                            <option value="">[Seleccione]</option>
                        </select>
                    </div>
                </div>

                <!-- Segunda columna -->
                
              <div class="col-sm-6">
                                 

                    <div class="form-group">
                        <label for="id_fechaNacimiento" class="control-label">Fecha de
                           creacion </label> <input class="form-control" type="date"
                                                      id="id_fechaCreacion" name="fechaCreacion">
                    </div>

                </div>
            </div>

            <div class="row" style="margin-top: 2%" align="center">
                <button id="id_btn_registrar" type="button" class="btn btn-primary">Registrar Tesis</button>
            </div>
        </form>
    </div>
	
	
	</div>
	
<script type="text/javascript">

// Creamos las validaciones
$(document)
.ready(
    function() {
        $('#id_form')
            .bootstrapValidator(
                {
                    message : 'This value is not valid',
                    feedbackIcons : {
                        valid : 'glyphicon glyphicon-ok',
                        invalid : 'glyphicon glyphicon-remove',
                        validating : 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                    	
                        titulo: {
                            validators: {
                                notEmpty: {
                                    message: 'El titulo es requerido'
                                },
                            }
                        },
                        
                        tema: {
                            validators: {
                                notEmpty: {
                                    message: 'El Tema es requerido'
                                },
                            }
                        },
                        
                        idAlumno: {
                            validators: {
                                notEmpty: {
                                    message: 'Debe seleccionar nombre del Alumno'
                                }
                            }
                        },
                        
                       
                        fechaCreacion: {
                            selector : "#id_fechaCreacion",
                            validators : {
                                notEmpty : {
                                    message : 'La fecha de creación es requerida'
                                },
                            }
                        }
                        
                    }
                });
    });
    

// Codigo para registrar tesis

    $("#id_btn_registrar").click(function() {
        var validator = $('#id_form').data('bootstrapValidator');
        validator.validate();

        if (validator.isValid()) {
            $.ajax({
                type: "POST",
                url: "registraTesis",
                data: $('#id_form').serialize(),
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


    
// Cargar alumnos para el combo
$.getJSON("cargaAlumnos", {}, function(data) {
    $.each(data, function(index, item) {
        $("#id_alumno").append("<option value=" + item.idAlumno + ">" + item.nombres + " " + item.apellidos + "</option>");
    });
});

function limpiarFormulario() {
    $('#id_nombres').val('');
    $('#id_apellidos').val('');
    $('#id_telefono').val('');
    $('#id_dni').val('');
    $('#id_correo').val('');
    $('#id_fechaNacimiento').val('');
    $('#id_pais').val('');
}




</script>
	
	
	
</body>
</html>




