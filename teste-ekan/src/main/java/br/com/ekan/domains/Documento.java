package br.com.ekan.domains;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ekan.domains.enums.EnumTipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Documento {
	
	@Column(name="tipo_documento")
	private EnumTipoDocumento tipoDocumento;
	private String descricao;
	@Column(name="data_inclusao")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime dataInclusao;
	
	@Column(name="data_atualizacao")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime dataAtualizacao;

}
