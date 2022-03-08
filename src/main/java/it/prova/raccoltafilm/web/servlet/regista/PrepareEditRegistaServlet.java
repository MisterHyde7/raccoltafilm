package it.prova.raccoltafilm.web.servlet.regista;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Regista;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.service.RegistaService;

@WebServlet("/PrepareEditRegistaServlet")
public class PrepareEditRegistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idInput = request.getParameter("idRegista");
		Long idLong = Long.parseLong(idInput);

		try {
			RegistaService registaInstance = MyServiceFactory.getRegistaServiceInstance();
			Regista registaDaModificare = registaInstance.caricaSingoloElemento(idLong);
			request.setAttribute("registaDaModificare", registaDaModificare);
		} catch (Exception e) {

		}

		request.getRequestDispatcher("/regista/edit.jsp").forward(request, response);
	}

}
