package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> todas() {
		return manager.createQuery("from Restaurante", Restaurante.class)	
		    .getResultList();
	}
	
	@Override
	public Restaurante porId(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Override
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		restaurante = porId(restaurante.getId());
		manager.remove(restaurante);
	}
}
