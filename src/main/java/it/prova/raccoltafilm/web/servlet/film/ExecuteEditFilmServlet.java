package it.prova.raccoltafilm.web.servlet.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/ExecuteEditFilmServlet")
public class ExecuteEditFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// estraggo input
		String titoloInputParam = request.getParameter("titolo");
		String genereInputParam = request.getParameter("genere");
		String durataInputStringParam = request.getParameter("durata");
		String dataPubblicazioneStringParam = request.getParameter("dataPubblicazione");
		String idStringParam = request.getParameter("idFilm");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Film filmInstance = UtilityForm.createFilmFromParams(titoloInputParam, genereInputParam, durataInputStringParam, dataPubblicazioneStringParam, idStringParam);

		// se la validazione non risulta ok
		if (!UtilityForm.validateFilmBean(filmInstance)) {
			request.setAttribute("editFilm", filmInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/film/edit.jsp").forward(request, response);
			return;
		}
		filmInstance.setId(Long.parseLong(idStringParam));

		try {
			MyServiceFactory.getFilmServiceInstance().aggiorna(filmInstance);
			request.setAttribute("film_list_attribute", MyServiceFactory.getFilmServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		response.sendRedirect("ExecuteListFilmServlet?operationResult=SUCCESS");

	}
}
