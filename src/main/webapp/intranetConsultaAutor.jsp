<!DOCTYPE html>
<html lang="esS">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/global.js"></script>
<script type="text/javascript" src="js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="css/bootstrap.css" />
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrapValidator.css" />

<title>Sistemas - Jorge Jacinto Gutarra</title>
</head>
<body>
	<jsp:include page="intranetCabecera.jsp" />
	<div class="container" style="margin-top: 4%">
		<h4>Consulta Autor</h4>

		<div class="row" style="margin-top: 5%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_nombre">Nombres</label> <input
					class="form-control" type="text" id="id_nombre" name="nombre">
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="id_telefono">Teléfono</label> <input
					class="form-control" type="text" id="id_telefono" name="telefono">
			</div>
		</div>
		
			<div class="row" style="margin-top: 2%">
			<div class="form-group col-md-6">
				<label class="control-label" for="id_fecha_desde">Fecha Nacimiento(Desde)</label>
				<input class="form-control" type="date" id="id_fecha_desde" name="fechaDesde" >
			</div>
			<div class="form-group col-md-6">
				<label class="control-label" for="id_fecha_hasta">Fecha Nacimiento(Hasta)</label>
				<input class="form-control" type="date" id="id_fecha_hasta" name="fechaHasta" >
			</div>
	</div>
	<div class="row"  align="center" style="margin-top: 2%">
			<div class="form-group col-md-12">
					<button type="button" class="btn btn-primary" id="id_btn_filtrar">Filtrar</button>
			</div>
	</div>
		
		
		<div class="row" style="margin-top: 3%">
			<div class="col-md-12">
				<table id="id_table"
					class="table table-bordered table-hover table-condensed">
					<thead style='background-color: #337ab7; color: white'>
						<tr>
							<th>Código</th>
							<th>Nombres</th>
							<th>Apellidos</th>
							<th>fechaNacimiento</th>
							<th>Teléfono</th>
							<th>Estado</th>
							<th>Grado</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	$("#id_btn_filtrar").click(function() {
		var vfnombre = $("#id_nombre").val();
		var vtelefono = $("#id_telefono").val();
		var vfdesde = $("#id_fecha_desde").val();
		var vfhasta = $("#id_fecha_hasta").val();
		
		console.log("vfnombre >>> " + vfnombre);   //obs
		console.log("vtelefono >>> " + vtelefono);
		console.log("vfdesde >>> " + vfdesde);
		console.log("vfhasta >>> " + vfhasta);     //s
		
		$.getJSON("consultaAutorServlet", {"nombres":vfnombre,"telefono":vtelefono,"desde":vfdesde,"hasta":vfhasta}, function(data) {
			agregarGrilla(data);
		});    
	});
	
	function agregarGrilla(lista){
		 $('#id_table').DataTable().clear();
		 $('#id_table').DataTable().destroy();
		 $('#id_table').DataTable({
				data: lista,
				language: IDIOMA,
				searching: true,
				ordering: true,
				processing: true,
				pageLength: 10,
				lengthChange: false,
				info:true,
				scrollY: 305,
		        scroller: {
		            loadingIndicator: true
		        },
				columns:[
					{data: "idAutor",className:'text-center'},
					{data: "nombres",className:'text-center'},
					{data: "apellidos",className:'text-center'},
					{data: "fechaFormateada", className:'text-center'},
					{data: "telefono", className:'text-center'},
					
					{data: function(row, type, val, meta){
						return row.estado == 1 ? "Activo" : "Inactivo";  
					},className:'text-center'},
					{data: "grado.descripcion",className:'text-center'},
				]                                     
		    });
	}
	
	</script>
	
	
	
	
</body>
</html>





