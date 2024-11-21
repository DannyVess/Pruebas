package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;

import com.google.gson.Gson;

import entity.Libro;
import entity.Categoria;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLibro;

@WebServlet("/registraLibro")
public class RegistraLibroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String tit = req.getParameter("titulo");
        String anio = req.getParameter("anio");
        String serie = req.getParameter("serie");
        String tema = req.getParameter("tema");        
        String cat = req.getParameter("categoria");

        Libro objLibro = new Libro();
        objLibro.setTitulo(tit);
        objLibro.setAnio(Integer.parseInt(anio));
        objLibro.setSerie(serie);
        objLibro.setTema(tema);
        objLibro.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        objLibro.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
        objLibro.setEstado(1);
        
        Categoria  objCat = new Categoria();
        objCat.setIdCategoria(Integer.parseInt(cat));

        objLibro.setCategoria(objCat);

        ModelLibro modelLibro = new ModelLibro();
        int salida = modelLibro.insertarLibro(objLibro);

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