package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Estado> todas() {
		return manager.createQuery("from Estado", Estado.class)	
		    .getResultList();
	}
	
	@Override
	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Override
	@Transactional
	public Estado adicionar(Estado estado) {
		return manager.merge(estado);
	}
	
	@Override
	@Transactional
	public void remover(Long id) {
		Estado estado = porId(id);
		
		 if (estado == null) {
	            throw new EmptyResultDataAccessException(1);
	        }
		manager.remove(estado);
	}
}
