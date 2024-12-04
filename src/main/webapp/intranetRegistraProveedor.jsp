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
    <h4>Registra Proveedor</h4>
        
        
    <div class="container">
       <form action=" "  id="id_form">
        
        
            <div class="row" style="margin-top: 4%">
                <!-- Razón Social -->
                <div class="form-group col-md-6">
                    <label class="control-label" for="idRazonSocial">Razón Social</label>
                    <input class="form-control" type="text" id="idRazonSocial" name="razonSocial" placeholder="Ingrese Razón Social" required>
                </div>
                <!-- RUC -->
                <div class="form-group col-md-6">
                    <label class="control-label" for="idRUC">RUC</label>
                    <input class="form-control" type="text" id="idRUC" name="ruc" placeholder="Ingrese el RUC" maxlength="11" required>
                </div>
            </div>
            <div class="row" style="margin-top: 2%">
                <!-- Dirección -->
                <div class="form-group col-md-6">
                    <label class="control-label" for="idDireccion">Dirección</label>
                    <input class="form-control" type="text" id="idDireccion" name="direccion" placeholder="Ingrese la dirección" required>
                </div>
                <!-- Celular -->
                <div class="form-group col-md-3">
                    <label class="control-label" for="idCelular">Celular</label>
                    <input class="form-control" type="text" id="idCelular" name="celular" placeholder="Ingrese el celular" maxlength="9" required>
                </div>
                <!-- Contacto -->
                <div class="form-group col-md-3">
                    <label class="control-label" for="idContacto">Contacto</label>
                    <input class="form-control" type="text" id="idContacto" name="contacto" placeholder="Ingrese el contacto" required>
                </div>
            </div>
            <div class="row" style="margin-top: 2%">
               
               <div class="form-group col-md-6">
                        <label for="id_pais" class="control-label">Pa&iacute;s</label>
                        <select class="form-control" id="id_pais" name="pais">
                            <option value="">[Seleccione]</option>
                        </select>
               </div>
               
             </div>
               
                              
                <div class="row" style="margin-top: 2%" align="center">
                <button id="id_btn_registrar" type="button" class="btn btn-primary">Crear Proveedor</button>
               </div>
       
         </form>
      </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#id_form').bootstrapValidator({
                message: 'Este valor no es válido',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    razonSocial: {
                        validators: {
                            notEmpty: {
                                message: 'La razón social es obligatoria'
                            }
                        }
                    },
                    ruc: {
                        validators: {
                            notEmpty: {
                                message: 'El RUC es obligatorio'
                            },
                            regexp: {
                                regexp: /^[0-9]{11}$/,
                                message: 'El RUC debe tener 11 dígitos'
                            }
                        }
                    },
                    direccion: {
                        validators: {
                            notEmpty: {
                                message: 'La dirección es obligatoria'
                            }
                        }
                    },
                    celular: {
                        validators: {
                            notEmpty: {
                                message: 'El celular es obligatorio'
                            },
                            regexp: {
                                regexp: /^9[0-9]{8}$/,
                                message: 'El celular debe tener 9 dígitos y comenzar con 9'
                            }
                        }
                    },
                    contacto: {
                        validators: {
                            notEmpty: {
                                message: 'El contacto es obligatorio'
                            }
                        }
                    },
                    
                    pais: {
                        validators: {
                            notEmpty: {
                                message: 'Debe seleccionar un país'
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
                            url: "registraProveedor",
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
            $('#idRazonSocial').val('');
            $('#idRUC').val('');
            $('#idDireccion').val('');
            $('#idCelular').val('');
            $('#idContacto').val('');
            $('#idFechaRegistro').val('');
            $('#id_pais').val('');
        }
    </script>
</body>
</html>