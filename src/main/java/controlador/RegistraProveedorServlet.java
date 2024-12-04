package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import com.google.gson.Gson;

import entity.Proveedor;
import entity.Pais;
import entity.Respuesta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelProveedor;


@WebServlet("/registraProveedor")


public class RegistraProveedorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String raz = req.getParameter("razonSocial");
        String ruc = req.getParameter("ruc");
        String dir = req.getParameter("direccion");
        String cel = req.getParameter("celular");
        String con = req.getParameter("contacto");	
        String pa = req.getParameter("pais");

        Proveedor objProveedor = new Proveedor();
        objProveedor.setRazonsocial(raz);
        objProveedor.setRuc(ruc);
        objProveedor.setDireccion(dir);
        objProveedor.setCelular(cel);
        objProveedor.setContacto(con);
        objProveedor.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
        objProveedor.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));
        objProveedor.setEstado(1);

        Pais  objPais = new Pais();
        objPais.setIdPais(Integer.parseInt(pa));

        objProveedor.setPais(objPais);

        ModelProveedor modelProveedor = new ModelProveedor();
        int salida = modelProveedor.insertarProveedor(objProveedor);

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
