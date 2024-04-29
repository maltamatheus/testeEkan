package br.com.ekan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ekan.domains.Beneficiario;
import br.com.ekan.domains.enums.EnumStatusRemove;
import br.com.ekan.repositories.BeneficiarioRepositoryImpl;

@RestController
@RequestMapping("/teste-ekan/beneficiarios/")
public class BeneficiarioController {
	
	@Autowired
	private BeneficiarioRepositoryImpl beneficiarioRepository;
	
	@GetMapping("consultar")
	public List<Beneficiario> listarBeneficiarios(){
		return beneficiarioRepository.findAll();
	}
	
	@GetMapping("consultar/{id}")
	public ResponseEntity<Beneficiario> consultarBeneficiario(@PathVariable Long id){
		
		Beneficiario beneficiario = beneficiarioRepository.findById(id);
		
		if (beneficiario != null) {
			return ResponseEntity.ok(beneficiario);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<Beneficiario> cadastrarBeneficiario(@RequestBody Beneficiario beneficiario){
		return ResponseEntity.status(HttpStatus.CREATED).body(beneficiarioRepository.save(beneficiario));
	}
	
	@DeleteMapping("remover/{id}")
	public ResponseEntity<EnumStatusRemove> removerBeneficiario(@PathVariable Long id) {
		if(beneficiarioRepository.remove(id).equals(EnumStatusRemove.REMOVIDO_COM_SUCESSO)) {
			return ResponseEntity.ok(EnumStatusRemove.REMOVIDO_COM_SUCESSO);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(EnumStatusRemove.BENEFICIARIO_NAO_ENCONTRADO);
	}
	
	@PutMapping("atualizar")
	public ResponseEntity<Beneficiario> atualizarBeneficiario(@RequestBody Beneficiario beneficiario){
		Beneficiario beneficiarioSaida = beneficiarioRepository.update(beneficiario);
		
		if (beneficiarioSaida != null) {
			return ResponseEntity.ok(beneficiarioSaida);
		}
		
		return ResponseEntity.notFound().build();
	}

}
