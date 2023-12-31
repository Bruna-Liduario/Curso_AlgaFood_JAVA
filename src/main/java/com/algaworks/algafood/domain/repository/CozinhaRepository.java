package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> todas();
	List<Cozinha> consultarPorNome(String nome);
	Cozinha porId(Long id);

	Cozinha adicionar(Cozinha cozinha);
	void remover(Long id);
	
}
