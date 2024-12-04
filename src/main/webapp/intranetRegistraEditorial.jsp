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
	<h4>Registra Editorial</h4>
	
	<div class="container">
        <form action=" "  id="id_form">
            <div class="row" style="margin-top: 5%">
                <!-- Primera columna -->
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="id_razonSocial" class="control-label">Razón Social</label> <input
                            class="form-control" type="text" id="id_razonSocial" name="razonSocial"
                            placeholder="Ingrese Razón Social" maxlength="45">
                    </div>
                    <div class="form-group">
                        <label for="id_direccion" class="control-label">Dirección</label> <input
                            class="form-control" type="text" id="id_direccion" name="direccion"
                            placeholder="Ingrese la Dirección" maxlength="200">
                    </div>
                    <div class="form-group">
                        <label for="id_telefono" class="control-label">Tel&eacute;fono</label> <input
                            class="form-control" type="text" id="id_telefono" name="telefono"
                            placeholder="Ingrese el tel&eacute;fono" maxlength="9">
                    </div>
                    
                </div>

                <!-- Segunda columna -->
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="id_ruc" class="control-label">RUC</label> <input
                            class="form-control" type="text" id="id_ruc" name="ruc"
                            placeholder="Ingrese el RUC" maxlength="11">
                    </div>
                   
                    <div class="form-group">
                        <label for="id_fechaCreacion" class="control-label">Fecha Creación</label> 
                        <input class="form-control" type="date" id="id_fechaCreacion" name="fechaCreacion">
                    </div>
                    
                    <div class="form-group">
                        <label for="id_pais" class="control-label">Pa&iacute;s</label>
                        <select class="form-control" id="id_pais" name="pais">
                            <option value="">[Seleccione]</option>
                        </select>
                    </div>

                </div>
            </div>

            <div class="row" style="margin-top: 2%" align="center">
                <button id="id_btn_registrar" type="button" class="btn btn-primary">Crear Editorial</button>
            </div>
        </form>
    </div>
</div>

<script type="text/javascript">
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
                                razonSocial: {
                                    validators: {
                                        notEmpty: {
                                            message: 'La Razón Social es requerida'
                                        },
                                    }
                                },
                                direccion: {
                                    validators: {
                                        notEmpty: {
                                            message: 'La Dirección es requerida'
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
                                ruc: {    
                                  selector: "#id_ruc",
                                    validators:{
                                        notEmpty: {
                                            message: 'El RUC es obligatorio'
                                        },
                                        regexp: {
                                            regexp: /^[0-9]{11}$/,
                                            message: 'El RUC es 11 digitos'
                                        }
                                    }
                                },                                
                                fechaCreacion: {
                                    selector : "#id_fechaCreacion",
                                    validators : {
                                        notEmpty : {
                                            message : 'La fecha de creacion es requerida'
                                        },
                                    }
                                },
                                pais: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Debe seleccionar un pa&iacute;s'
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
                url: "registraEditorial",
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

    $.getJSON("cargaPais", {}, function(data) {
        $.each(data, function(index, item) {
            $("#id_pais").append("<option value=" +  item.idPais +" >" + item.nombre + "</option>");
        });
    });

   function limpiarFormulario() {
        $('#id_razonSocial').val('');
        $('#id_direccion').val('');
        $('#id_telefono').val('');
        $('#id_ruc').val('');
        $('#id_fechaCreacion').val('');
        $('#id_pais').val('');
    }
</script>
</body>
</html>