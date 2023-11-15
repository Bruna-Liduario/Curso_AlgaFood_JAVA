package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

	@Autowired
	private NotificadorProperties properties;
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Host: " + properties.getHostServidor());
		System.out.printf("Porta: " + properties.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s usando SMTP: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}

}
