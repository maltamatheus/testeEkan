package br.com.ekan.domains;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="tab_beneficiarios")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="beneficiario_id")
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name="data_inclusao")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime dataInclusao;
	
	@Column(name="data_atualizacao")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime dataAtualizacao;
	
	@ElementCollection
	@CollectionTable(name="tab_beneficiario_documentos",
	joinColumns = @JoinColumn(name="beneficiario_documentos_id"))
	private List<Documento> documentos;
	
	public List<Documento> getDocumentos(){
		if (this.documentos == null) {
			return new ArrayList<Documento>();
		}
		
		return this.documentos;
	}

}
