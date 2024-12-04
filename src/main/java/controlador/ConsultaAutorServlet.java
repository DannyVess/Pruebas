package controlador;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import com.google.gson.Gson;

import entity.Autor;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelAutor;

@WebServlet("/consultaAutorServlet")
public class ConsultaAutorServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1 Recibir el filtro
		String nom = req.getParameter("nombres");
		String tel = req.getParameter("telefono");
		String desde = req.getParameter("desde");
		String hasta = req.getParameter("hasta");

		if (desde.isEmpty()) {
			desde = "1900-01-01";
		}
		if (hasta.isEmpty()) {
			hasta = "3000-01-01";
		}

		Date dateDesde = Date.valueOf(desde);
		Date dateHasta = Date.valueOf(hasta);

		// 2 Invocar al modelo
		ModelAutor model = new ModelAutor();
		List<Autor> lista = model.listaAutorComplejo("%" + nom + "%", tel, dateDesde, dateHasta);

		// 3 Enviar la lista a la vista
		Gson gson = new Gson();
		String json = gson.toJson(lista);

		// 4 Respuesta al browser
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().print(json);    //write

	}
	
	

}
