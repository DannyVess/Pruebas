package entity;

import java.sql.Timestamp;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Prestamo {

	private int idPrestamo;
	private Timestamp fechaPrestamo;
	private Timestamp fechaDevolucion;
	private Usuario usuario;
	private List<PrestamoHasLibro> detallesPrestamos;

}
