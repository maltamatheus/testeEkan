package br.com.ekan.domains;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tab_beneficiarios")
public class Beneficiario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="beneficiario_id")
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private LocalDate dataNascimento;
	
	private LocalDateTime dataInclusao;
	
	private LocalDateTime dataAtualizacao;

}
