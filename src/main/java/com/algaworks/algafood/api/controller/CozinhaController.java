package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@Controller
@ResponseBody
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	
	@GetMapping //(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {
		return cozinhaRepository.todas();
	}
	

	//@ResponseStatus(HttpStatus.CREATED) -> anotação serve para retornar um código status http 201(created) se bem sucedido e resultou na criação de novo recurso. 
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long cozinhaId) {
		Cozinha cozinha = cozinhaRepository.porId(cozinhaId);
		
		if(cozinha != null) {
			return ResponseEntity.ok(cozinha);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinha.salvar(cozinha);
	}
	
	
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable long cozinhaId, 
			@RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaRepository.porId(cozinhaId);
	
		if(cozinhaAtual != null) {
		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");		
		
		cozinhaAtual = cadastroCozinha.salvar(cozinhaAtual);
		
		return ResponseEntity.ok(cozinhaAtual);
		}
		
		return ResponseEntity.notFound().build();		
	 }
	
	
	
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
	
	  try {
		cadastroCozinha.excluir(cozinhaId);	
		return ResponseEntity.noContent().build();
						
	  } catch (EntidadeNaoEncontradaException e) {
		  return ResponseEntity.notFound().build();
		  
	  }catch (EntidadeEmUsoException e) {
		  return ResponseEntity.status(HttpStatus.CONFLICT).build();
	  }
	}
		
}
	

