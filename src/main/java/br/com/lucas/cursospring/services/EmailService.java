package br.com.lucas.cursospring.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.lucas.cursospring.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);

}
