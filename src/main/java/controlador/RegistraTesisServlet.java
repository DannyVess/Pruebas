package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.gson.Gson;

import entity.Alumno;
import entity.Respuesta;
import entity.Tesis;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelTesis;

@WebServlet("/registraTesis")
public class RegistraTesisServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	
    	String tit = req.getParameter("titulo");
        String tem = req.getParameter("tema");
        String al  = req.getParameter("idAlumno");        

        String fecCrea = req.getParameter("fechaCreacion");
        

        Tesis objTesis = new Tesis();
        objTesis.setTitulo(tit);
        objTesis.setTema(tem);
            
        objTesis.setFechaCreacion(Date.valueOf(fecCrea));
        objTesis.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        objTesis.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
        
        // Convertir estado a entero
        objTesis.setEstado(1);
               
        Alumno  objAlumno = new Alumno();
        objAlumno.setIdAlumno(Integer.parseInt(al));

        objTesis.setAlumno(objAlumno);

        ModelTesis modelTesis = new ModelTesis();
        int salida = modelTesis.insertarTesis(objTesis);

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