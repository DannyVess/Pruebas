package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.gson.Gson;

import entity.Editorial;
import entity.Pais;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelEditorial;

@WebServlet("/registraEditorial")
public class RegistraEditorialServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Datos del Editorial
    	String raz = req.getParameter("razonSocial");
        String dire = req.getParameter("direccion");
        String tel = req.getParameter("telefono");
        String ruc = req.getParameter("ruc");        	
        String fecCre = req.getParameter("fechaCreacion");
        String pa = req.getParameter("pais");
        //String fecFor = req.getParameter("fechaFormateada");       
     	
     	// Crear objeto Editorial
        Editorial objEditorial = new Editorial();
        objEditorial.setRazonSocial(raz);
        objEditorial.setDireccion(dire);
        objEditorial.setTelefono(tel);
        objEditorial.setRuc(ruc);        
        objEditorial.setFechaCreacion(Date.valueOf(fecCre));
        objEditorial.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        objEditorial.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
        objEditorial.setEstado(1);        
        //objEditorial.setFechaFormateada(fecFor);        
        
        // Crear objeto Pais
     	Pais pais = new Pais();
     	pais.setIdPais(Integer.parseInt(pa));
     	
     	objEditorial.setPais(pais);
               
        // Registrar editorial
        ModelEditorial modelEditorial = new ModelEditorial(); 
        int salida = modelEditorial.insertarEditorial(objEditorial);

        Respuesta objRespuesta = new Respuesta();

        if (salida > 0) {
            objRespuesta.setMensaje("Registro exitoso");
        }else {
            objRespuesta.setMensaje("Error en el registro");
        }

        // Convertir a JSON
        Gson gson = new Gson();
        String json = gson.toJson(objRespuesta);

        resp.setContentType("application/json;charset=UTF-8");
		
        PrintWriter out = resp.getWriter();
        out.println(json);
    }

}
