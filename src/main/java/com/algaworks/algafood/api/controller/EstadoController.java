package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@Controller
@ResponseBody
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@GetMapping
	public List<Estado> porId() {
		return estadoRepository.todas();
	}
	
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {

		Estado estado = estadoRepository.porId(estadoId);
		
		if(estadoId != null) {
			return ResponseEntity.ok(estado);
		}
		return ResponseEntity.notFound().build();	
	}	
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstado.salvar(estado);
	}
	
	
	@PutMapping ("/{estadoId}")
	public ResponseEntity<Estado> atualizar (@PathVariable Long estadoId, 
			@RequestBody Estado estado){
		
		Estado estadoAtual = estadoRepository.porId(estadoId);
		
		if(estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");
			
			estadoAtual = cadastroEstado.salvar(estadoAtual);
			return ResponseEntity.ok(estadoAtual);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try { 
		cadastroEstado.excluir(estadoId);
		 return ResponseEntity.noContent().build();
	
	} catch (EntidadeNaoEncontradaException e) {
		return ResponseEntity.notFound().build();
		
	} catch (EntidadeEmUsoException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(e.getMessage());
	}
  }

	
}

