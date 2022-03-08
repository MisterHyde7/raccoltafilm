package it.prova.raccoltafilm.dao;

import java.util.List;

import it.prova.raccoltafilm.model.Regista;

public interface RegistaDAO extends IBaseDAO<Regista> {
	
	public List<Regista> findByExample(Regista example) throws Exception;
	
	public Regista findOneWithFilms(Long id) throws Exception;

	void delete(Regista input) throws Exception;
}
