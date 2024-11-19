package entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tesis {

	private int idTesis;
	private String titulo;
	private String tema;
	private Date fechaCreacion;
	private Timestamp fechaRegistro;
	private Timestamp fechaActualizacion;
	private int estado;
	private Alumno alumno;
	
}
