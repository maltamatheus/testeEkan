package br.com.ekan.repositories;

import java.util.List;
import java.util.Map;

import br.com.ekan.domains.Beneficiario;
import br.com.ekan.domains.enums.EnumStatusRemove;

public interface BeneficiarioRepository{
	
	List<Beneficiario> findAll();
	
	Beneficiario findById(Long id);
	
	Beneficiario save(Beneficiario beneficiario);
	
	Beneficiario update(Beneficiario beneficiario);
	
	EnumStatusRemove remove(Long id);
	
}
