package controlador;

import java.io.IOException;
import java.util.List;

import entity.Opcion;
import entity.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ModelUsuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String user = request.getParameter("loginuser");
		String clave = request.getParameter("loginpassword");

		Usuario bean = new Usuario();
		bean.setLogin(user);
		bean.setPassword(clave);
		
		ModelUsuario dao = new ModelUsuario();
		
		try {
				
			Usuario usuario = dao.login(bean);
			//Cuando el usuario no existe
			if(usuario == null){
				String mensaje ="El usuario no existe";
				request.setAttribute("mensaje", mensaje);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
				
			}else{
				//Acceder a la session
				HttpSession session = request.getSession();
				
				//Se guarda en session los datos de usuario
				//La session es un objeto que dura un determinado tiempo en el servidor
				session.setAttribute("objUsuario", usuario);
				
				//Se obtiene los menus del usuario logeado y se guarda en la memoria sesion
				List<Opcion> menus = dao.traerEnlacesDeUsuario(usuario.getIdUsuario());
				session.setAttribute("objMenus", menus);
				
				//Cuando el usuario existe
				response.sendRedirect("intranetHome.jsp");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}








