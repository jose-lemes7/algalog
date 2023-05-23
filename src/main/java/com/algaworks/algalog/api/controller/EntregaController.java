package com.algaworks.algalog.api.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Destinatario;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaServce;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private SolicitacaoEntregaServce solicitacaoEntregaServce;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@RequestBody Entrega entrega) {
		return solicitacaoEntregaServce.solicitar(entrega);
	}
	
	@GetMapping
	public Entrega test() {
		Cliente c = new Cliente();
		c.setId(2L);
		
		Destinatario d = new Destinatario();
		d.setBairro("");
		d.setComplemento("");
		d.setLogradouro("");
		d.setNome("");
		d.setNumero("");
		
		Entrega e = new Entrega();
		e.setDataPedido(LocalDateTime.now());
		e.setDataFinalizacao(null);
		e.setStatus(StatusEntrega.PENDENTE);
		e.setTaxa(BigDecimal.valueOf(4.50));
		
		e.setCliente(c);
		e.setDestinatario(d);
		
		return e;
	}
}
