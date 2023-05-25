package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
	
	private BuscarEntregaService buscarEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {		
		Entrega entrega = buscarEntregaService.buscarEntrega(entregaId);		
		
		return entrega.adicionarOcorrencia(descricao);
	}
	
}
