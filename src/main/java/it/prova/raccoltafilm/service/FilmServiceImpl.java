package it.prova.raccoltafilm.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.raccoltafilm.dao.FilmDAO;
import it.prova.raccoltafilm.exceptions.ElementNotFoundException;
import it.prova.raccoltafilm.model.Film;
import it.prova.raccoltafilm.web.listener.LocalEntityManagerFactoryListener;

public class FilmServiceImpl implements FilmService {

	private FilmDAO filmDAO;

	@Override
	public void setFilmDAO(FilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	@Override
	public List<Film> listAllElements() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return filmDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Film caricaSingoloElemento(Long id) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return filmDAO.findOne(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Film caricaSingoloElementoEager(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return filmDAO.findOneEager(id).orElse(null);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Film filmInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			filmDAO.update(filmInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Film filmInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			filmDAO.insert(filmInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Film filmInstance) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			filmDAO.delete(filmInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idFilmToRemove) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			filmDAO.setEntityManager(entityManager);
			Film filmToRemove = filmDAO.findOne(idFilmToRemove);
			if (filmToRemove == null)
				throw new ElementNotFoundException("Film con id: " + idFilmToRemove + " non trovato.");

			filmDAO.delete(filmToRemove);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public List<Film> findByExample(Film example) throws Exception {
		
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			filmDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return filmDAO.findByExample(example);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
