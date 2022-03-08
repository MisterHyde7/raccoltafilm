package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.FilmService;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/PrepareEditFilmServlet")
public class PrepareEditFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idInput = request.getParameter("idFilm");
		Long idLong = Long.parseLong(idInput);

		try {
			FilmService filmInstance = MyServiceFactory.getFilmServiceInstance();
			Film filmDaModificare = filmInstance.caricaSingoloElementoEager(idLong);
			request.setAttribute("filmDaModificare", filmDaModificare);
			request.setAttribute("listaRegisti", MyServiceFactory.getRegistaServiceInstance().listAllElements());
		} catch (Exception e) {

		}

		request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
	}

}
