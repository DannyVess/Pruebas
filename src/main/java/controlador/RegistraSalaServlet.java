package controlador;

import java.io.IOException;
import java.sql.Timestamp;

import com.google.gson.Gson;

import entity.Respuesta;
import entity.Sala;
import entity.Sede;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelSala;

@WebServlet("/registraSala")
public class RegistraSalaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
		
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		// Datos de la sala
		String numero = req.getParameter("numero");
		String piso = req.getParameter("piso");
		String numAlumnos = req.getParameter("cantidad");
		String recursos = req.getParameter("recursos");
		String idSede = req.getParameter("sede");
		
		// Crear objeto Sede
		Sede sede = new Sede();
		sede.setIdSede(Integer.parseInt(idSede));
				
		// Crear objeto Sala
		Sala sala = new Sala();
		sala.setNumero(numero);
		sala.setPiso(Integer.parseInt(piso));
        sala.setNumAlumnos(Integer.parseInt(numAlumnos));
		sala.setRecursos(recursos);
		sala.setEstado(1);
		sala.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
		sala.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
		sala.setSede(sede);

		// Registrar Sala
		ModelSala model = new ModelSala();
		int salida = model.insertaSala(sala);
		
		Respuesta obj = new Respuesta();
		if(salida > 0) {
            obj.setMensaje("Sala registrado correctamente");
		}else {
            obj.setMensaje("Error al registrar la sala");
        }
		
		// Convertir a JSON
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(json);
		
	}

	
	
}
