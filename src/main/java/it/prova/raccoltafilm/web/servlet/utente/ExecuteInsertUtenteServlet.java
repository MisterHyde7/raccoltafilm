package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Ruolo;
import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;
import it.prova.raccoltafilm.utility.UtilityForm;

@WebServlet("/utenti/ExecuteInsertUtenteServlet")
public class ExecuteInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// estraggo input
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String userNameParam = request.getParameter("username");
		String passwordParam = request.getParameter("password");
		String[] listaRuoli = request.getParameterValues("ruoli");
		Set<Long> listaIdRuoli = new HashSet<>();
		for (String stringItem : listaRuoli) {
			listaIdRuoli.add(Long.parseLong(stringItem));
		}
		Set<Ruolo> ruoliUtente = new HashSet<>();

		Utente utenteInstance = UtilityForm.createUtenteFromParams(nomeParam, cognomeParam, userNameParam,
				passwordParam, new Date());

		// se la validazione non risulta ok
		if (!UtilityForm.validateUtenteBean(utenteInstance)) {
			request.setAttribute("insert_utente_attr", utenteInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			for (Long longItem : listaIdRuoli) {
				ruoliUtente.add(MyServiceFactory.getRuoloServiceInstance().caricaSingoloElemento(longItem));
			}
			utenteInstance.setRuoli(ruoliUtente);
			MyServiceFactory.getUtenteServiceInstance().inserisciNuovo(utenteInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("insert.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListUtenteServlet?operationResult=SUCCESS");

	}
}
