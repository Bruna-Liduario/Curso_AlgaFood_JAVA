package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> todas() {
		return manager.createQuery("from Forma Pagamento", FormaPagamento.class)	
		    .getResultList();
	}
	
	@Override
	public FormaPagamento porId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Override
	@Transactional
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Override
	@Transactional
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = porId(formaPagamento.getId());
		manager.remove(formaPagamento);
	}
}
