package br.com.ekan.domains;

import java.time.LocalDateTime;

import br.com.ekan.domains.enums.EnumTipoDocumento;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Documento {
	
	private Long id;
	private EnumTipoDocumento tipoDocumento;
	private String descricao;
	private LocalDateTime dataInclusao;
	private LocalDateTime dataAtualizacao;

}
