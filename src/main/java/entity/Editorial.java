package entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Editorial {

	private int idEditorial;
	private String razonSocial;
	private String direccion;
	private String telefono;
	private String ruc;
	private Date fechaCreacion;
	private Timestamp fechaRegistro;
	private Timestamp fechaActualizacion;
	private int estado;
	private Pais pais;
	private String fechaFormateada;	
	
}
