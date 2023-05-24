package com.algaworks.algalog.api.model.input;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInput {
	
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
	@Valid
	@NotNull
	private DestinatarioInput destinatario;
	
	private BigDecimal taxa;
	
}