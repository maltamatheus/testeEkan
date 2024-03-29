package br.com.ekan.repositories;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ekan.domains.Beneficiario;
import br.com.ekan.domains.Documento;
import br.com.ekan.domains.enums.EnumStatusRemove;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class BeneficiarioRepositoryImpl implements BeneficiarioRepository {

	@PersistenceContext
	private EntityManager manager;

	private Beneficiario novoBeneficiario;

	@Override
	public List<Beneficiario> findAll() {
		return manager.createQuery("from Beneficiario", Beneficiario.class).getResultList();
	}

	@Override
	public Beneficiario findById(Long id) {
		return manager.find(Beneficiario.class, id);
	}

	@Override
	@Transactional
	public Beneficiario save(Beneficiario beneficiario) {

		if (beneficiario.getId() == null) {
			if (beneficiario.getDataInclusao() == null) {
				beneficiario.setDataInclusao(LocalDateTime.now());
			}
		}
		
		for (Documento documento : beneficiario.getDocumentos()) {
			if (documento.getDataInclusao() == null) {
				documento.setDataInclusao(LocalDateTime.now());
			}
			documento.setDataAtualizacao(LocalDateTime.now());
		}

		beneficiario.setDataAtualizacao(LocalDateTime.now());

		novoBeneficiario = manager.merge(beneficiario);

		return novoBeneficiario;
	}
	
	
	@Override
	@Transactional
	public Beneficiario update(Beneficiario beneficiario) {
		
		if(findById(beneficiario.getId()) != null) {
			return save(beneficiario);
		}
		return null;
	}

	@Override
	@Transactional
	public EnumStatusRemove remove(Long id) {

		Beneficiario beneficiario = findById(id);

		if (beneficiario != null) {
			manager.remove(beneficiario);
			return EnumStatusRemove.REMOVIDO_COM_SUCESSO;
		}
		return EnumStatusRemove.BENEFICIARIO_NAO_ENCONTRADO;
	}

	private void merge(Map<String, Object> dadosOrigem, Beneficiario beneficiarioDestino) {

		ObjectMapper mapper = new ObjectMapper();

		Beneficiario beneficiarioEntrada = mapper.convertValue(dadosOrigem, Beneficiario.class);
		
		dadosOrigem.forEach((atributo, valor) -> {
			Field campo = ReflectionUtils.findField(Beneficiario.class, atributo);
			campo.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(campo, beneficiarioEntrada);
			
			ReflectionUtils.setField(campo, beneficiarioDestino, valor);
		});

	}
}
