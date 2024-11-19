package entity;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Libro {

	private int idLibro;
	private String titulo;
	private int anio;
	private String serie;
	private String tema;
	private Timestamp fechaRegistro;
	private Timestamp fechaActualizacion;
	private int estado;
	private Categoria categoria;

	
}
