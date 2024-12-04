package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.gson.Gson;


import entity.Autor;
import entity.Grado;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ModelAutor;

@WebServlet("/registraAutor")

public class RegistraAutorServlet extends HttpServlet {
	
	  private static final long serialVersionUID = 1L;

	    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    	
	    	String nom = req.getParameter("nombres");
	        String ape = req.getParameter("apellidos");
	        String fecNac = req.getParameter("fechaNacimiento");
	        String tel = req.getParameter("telefono");
	        String grd = req.getParameter("grado");
	        
	        Autor objAutor = new Autor();
	        objAutor.setNombres(nom);
	        objAutor.setApellidos(ape);
	        objAutor.setFechaNacimiento(Date.valueOf(fecNac));
	        objAutor.setTelefono(tel);
	        objAutor.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
	        objAutor.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
	        objAutor.setEstado((short) 1); 
	        objAutor.setIdGrado(Integer.parseInt(grd));
	        
	        Grado  objGrado = new Grado();
	        objGrado.setIdGrado(Integer.parseInt(grd));

	        objAutor.setIdGrado(objGrado.getIdGrado()); 


	        ModelAutor modelAutor = new ModelAutor();
	        int salida = modelAutor.insertarAutor(objAutor);

	        Respuesta objRespuesta = new Respuesta();

	        if (salida > 0) {
	            objRespuesta.setMensaje("Registro exitoso");
	        }else {
	            objRespuesta.setMensaje("Error en el registro");
	        }

	        Gson gson = new Gson();
	        String json = gson.toJson(objRespuesta);

	        resp.setContentType("application/json;charset=UTF-8");

	        PrintWriter out = resp.getWriter();
	        out.println(json);
	    }
}
