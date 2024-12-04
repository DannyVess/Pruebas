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
	<h4>Registra Libro</h4>
	
		   <div class="container">
        <form action=" "  id="id_form">
            <div class="row" style="margin-top: 5%">
                <!-- Primera columna -->
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="id_titulo" class="control-label">Titulo</label> <input
                            class="form-control" type="text" id="id_titulo" name="titulo"
                            placeholder="Ingrese el Titulo" maxlength="50">
                    </div>
                    <div class="form-group">
                        <label for="id_anio" class="control-label">Año</label> <input
                            class="form-control" type="text" id="id_anio" name="anio"
                            placeholder="Ingrese el año" maxlength="4">
                    </div>
                    
                    <div class="form-group">
                        <label for="id_categoria" class="control-label">Cate&iacute;goria</label>
                        <select class="form-control" id="id_categoria" name="categoria">
                            <option value="">[Seleccione]</option>
                        </select>
                    </div>
                </div>

                <!-- Segunda columna -->
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="id_serie" class="control-label">Serie</label> <input
                            class="form-control" type="text" id="id_serie" name="serie"
                            placeholder="Ingrese la serie" maxlength="50">
                    </div>

                    <div class="form-group">
                        <label for="id_tema" class="control-label">Tema</label> <input
                            class="form-control" type="text" id="id_tema" name="tema"
                            placeholder="Ingrese el tema" maxlength="10">
                    </div>
                    

                </div>
            </div>

            <div class="row" style="margin-top: 2%" align="center">
                <button id="id_btn_registrar" type="button" class="btn btn-primary">Crear Libro</button>
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
                                titulo: {
                                    validators: {
                                        notEmpty: {
                                            message: 'El titulo es requerido'
                                        },
                                        remote: {
                                            message: 'El título ya existe',
                                            url: 'validaTituloLibroServlet',
                                            delay: 2000
                                        }
                                        
                                    }
                                },
                                anio: {
                                    validators: {
                                        notEmpty: {
                                            message: 'El año es requerido'
                                        },
                                        regexp: {
                                        	regexp: /^(199[0-9]|200[0-9]|201[0-9]|202[0-4])$/,
                                            message: 'El año debe tener 4 dígitos  y estar en el rango de 1990 a 2024'
                                        },                                      
                                        remote: {
                                            message: 'El año ya existe',
                                            url: 'validaAnioLibroServlet',
                                            delay: 2000
                                        }
                                        
                                        
                                    }
                                },
                                serie:{
                                    selector: "#id_serie",
                                    validators:{
                                        notEmpty: {
                                            message: 'El n° de serie es obligatorio'
                                        },
                                        remote: {
                                            message: 'El número de serie ya existe',
                                            url: 'validaSerieLibroServlet',
                                            delay: 2000
                                        }
                                        
                                    }
                                },
                                tema:{
                                    selector: "#id_tema",
                                    validators:{
                                        notEmpty: {
                                            message: 'El tema es obligatorio'
                                        },
                                    }
                                },

                                categoria: {
                                    validators: {
                                        notEmpty: {
                                            message: 'Debe seleccionar una categoría'
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
                url: "registraLibro",
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

    $.getJSON("cargaCategoria", {}, function(data) {
        $.each(data, function(index, item) {
            $("#id_categoria").append("<option value=" +  item.idCategoria +" >" + item.descripcion + "</option>");
        });
    });

   function limpiarFormulario() {
        $('#id_titulo').val('');
        $('#id_anio').val('');
        $('#id_serie').val('');
        $('#id_tema').val('');
        $('#id_categoria').val('');
    }
</script>
</body>
</html>