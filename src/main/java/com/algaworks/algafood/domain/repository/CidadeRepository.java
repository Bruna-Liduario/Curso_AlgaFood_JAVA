package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cidade;


public interface CidadeRepository {

	List<Cidade> todas();
	Cidade porId(Long id);
	Cidade adicionar(Cidade cidade);
	void remover(Long id);
	
}
