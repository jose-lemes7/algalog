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

import com.algaworks.algalog.domain.model.DestinatarioModel;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.EntregaModel;
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
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
		return solicitacaoEntregaServce.solicitar(entrega);
	}
	
	@GetMapping
	public List<Entrega> listar() {
		return entregaRepository.findAll();
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId	)
			.map(entrega -> {
				EntregaModel model = new EntregaModel();
				model.setId(entrega.getId());
				model.setNome(entrega.getCliente().getNome());
				model.setStatus(entrega.getStatus());
				model.setTaxa(entrega.getTaxa());
				model.setDataPedido(entrega.getDataPedido());
				model.setDataFinalizacao(entrega.getDataFinalizacao());
				model.setDestinatario(new DestinatarioModel());
				model.getDestinatario().setNome(entrega.getDestinatario().getNome());
				model.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
				model.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
				model.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
				model.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
				return ResponseEntity.ok(model);
			})
			.orElse(ResponseEntity.notFound().build());
		
	}
}
