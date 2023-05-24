package com.algaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInput;
import com.algaworks.algalog.domain.model.Entrega;

@Component
public class EntregaAssembler {

	@Autowired
	private ModelMapper mapper;
	
	public EntregaModel toModel(Entrega entrega) {
		return mapper.map(entrega, EntregaModel.class);
	}
	
	public List<EntregaModel> toCollection(List<Entrega> entregas) {
		return entregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput input) {
		return mapper.map(input, Entrega.class);
	}
}
