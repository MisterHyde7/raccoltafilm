package it.prova.raccoltafilm.web.servlet.utente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.raccoltafilm.model.Utente;
import it.prova.raccoltafilm.service.MyServiceFactory;

@WebServlet("/utenti/PrepareInsertUtenteServlet")
public class PrepareInsertUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
			request.setAttribute("listaRuoli", MyServiceFactory.getRuoloServiceInstance().listAll());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("insert_utente_attr", new Utente());
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}
	
}
