package com.algaworks.algalogapi.api.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaInout {
	
	@NotBlank
	private String descricao;
	
}
