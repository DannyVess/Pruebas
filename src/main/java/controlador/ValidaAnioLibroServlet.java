package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLibro;

@WebServlet("/validaAnioLibroServlet")
public class ValidaAnioLibroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String year = req.getParameter("anio");
		System.out.println("Anio : " + year);

		//int yearInt = Integer.parseInt(year);
		
		ModelLibro model = new ModelLibro();
		boolean existe = model.existeAnio(Integer.parseInt(year)); // traer el nombre del boolean del model
		if (existe) {
			resp.getWriter().print("{\"valid\":false}");
		} else {
			resp.getWriter().print("{\"valid\":true}");
		}
	}
}