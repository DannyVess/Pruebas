package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLibro;

@WebServlet("/validaSerieLibroServlet")
public class ValidaSerieLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ser = req.getParameter("serie");
		System.out.println("Serie : " + ser);

		ModelLibro model = new ModelLibro();
		boolean existe = model.existeSerie(ser); // traer el nombre del boolean del model
		if (existe) {
			resp.getWriter().print("{\"valid\":false}");
		} else {
			resp.getWriter().print("{\"valid\":true}");
		}
	}
}