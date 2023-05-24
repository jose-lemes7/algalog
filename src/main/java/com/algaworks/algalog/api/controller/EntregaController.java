package com.algaworks.algalog.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaServce;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaRepository entregaRepository;
	private SolicitacaoEntregaServce solicitacaoEntregaServce;
	private EntregaAssembler assembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody Entrega entrega) {
		Entrega entregaSolicitada = solicitacaoEntregaServce.solicitar(entrega);
		return assembler.toModel(entregaSolicitada);
	}
	
	@GetMapping
	public List<EntregaModel> listar() {
		List<Entrega> list = entregaRepository.findAll();
		return assembler.toCollection(list);
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
			.map(entrega -> ResponseEntity.ok(assembler.toModel(entrega)))
			.orElse(ResponseEntity.notFound().build());
		
	}
}
