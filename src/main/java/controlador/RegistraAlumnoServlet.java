package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.gson.Gson;

import entity.Alumno;
import entity.Pais;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelAlumno;

@WebServlet("/registraAlumno")
public class RegistraAlumnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String nom = req.getParameter("nombres");
        String ape = req.getParameter("apellidos");
        String tel = req.getParameter("telefono");
        String dni = req.getParameter("dni");
        String con = req.getParameter("correo");	
        String fecNac = req.getParameter("fechaNacimiento");
        String pa = req.getParameter("pais");

        Alumno objAlumno = new Alumno();
        objAlumno.setNombres(nom);
        objAlumno.setApellidos(ape);
        objAlumno.setTelefono(tel);
        objAlumno.setDni(dni);
        objAlumno.setCorreo(con);
        objAlumno.setFechaNacimiento(Date.valueOf(fecNac));
        objAlumno.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        objAlumno.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
        objAlumno.setEstado(1);
        
        Pais  objPais = new Pais();
        objPais.setIdPais(Integer.parseInt(pa));

        objAlumno.setPais(objPais);

        ModelAlumno modelAlumno = new ModelAlumno();
        int salida = modelAlumno.insertarAlumno(objAlumno);

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