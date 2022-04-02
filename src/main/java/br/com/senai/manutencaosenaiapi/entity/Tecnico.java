package br.com.senai.manutencaosenaiapi.entity;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tecnico {
	
	@Getter @Setter
	@EqualsAndHashCode.Include
	private Integer id;
	
	@Getter @Setter
	private String nomeCompleto;
	
	@Getter @Setter
	private LocalDate dataDeAdmissao;

}
