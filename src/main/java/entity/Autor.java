package entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Autor {

	private int idAutor;  
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private Timestamp  fechaActualizacion;
	private String telefono;
	private Timestamp  fechaRegistro;	
	private int estado;   //private short estado (para preguntar ? )
    private int idGrado;
	private Grado grado;
	//private String FechaNacimientoFormateada; --profesor este atributo no se encuentra en la bd
    
    private String fechaFormateada;
	
}
