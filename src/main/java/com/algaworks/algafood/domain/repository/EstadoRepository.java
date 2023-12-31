package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Estado;


public interface EstadoRepository {

	List<Estado> todas();
	Estado porId(Long id);
	Estado adicionar(Estado estado);
	void remover(Long id);
	
}
