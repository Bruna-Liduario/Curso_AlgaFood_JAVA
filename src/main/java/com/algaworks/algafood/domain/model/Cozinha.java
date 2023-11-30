package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

//@JsonRootName("gastronomia") altera o nome da classe em XML
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cozinha {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	//@JsonIgnore -> apagar o nome da representação. no caso apaga "nome: tailandesa" por exemplo
	//@JsonProperty("titulo") -> apenas para alterar o nome de representação 
	@Column(nullable = false)
	private String nome;
		

	
}
