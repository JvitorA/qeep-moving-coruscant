package br.com.qm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.dto.AumentoDTO;
import br.com.qm.dto.CriaSecretariaDTO;
import br.com.qm.dto.ResponseDTO;
import br.com.qm.dto.SecretariaCriadaDTO;
import br.com.qm.entity.Secretaria;
import br.com.qm.exception.PrefeituraNegocioException;
import br.com.qm.repository.ProjetoRepository;
import br.com.qm.repository.SecretariaRepository;
import br.com.qm.repository.ServidorRepository;

@Service
public class SecretariaService {

	@Autowired
	SecretariaRepository secretariaRepo;
	
	@Autowired
	ProjetoRepository projetoRepo;
	
	@Autowired
	ServidorRepository servidorRepo;
	
	public SecretariaCriadaDTO criaSecretaria(CriaSecretariaDTO criaSecretaria) throws PrefeituraNegocioException {
		
		if (secretariaRepo.findFirstByPasta(criaSecretaria.getPasta()) != null) {
			throw new PrefeituraNegocioException("Erro ao criar uma secretaria: Já existe uma secretaria com essa pasta!");
		}
		
		Secretaria secretaria = secretariaRepo.save(criaSecretaria.toEntity());
		
		return new SecretariaCriadaDTO(secretaria.getIdSecretaria(), secretaria.getNome(), 
				"Secretaria de " + secretaria.getPasta().getValue() + " criada com sucesso!" );
	}
	
	public List<Secretaria> listarSecretarias() {
		return (List<Secretaria>) secretariaRepo.findAll();
	}
	
	public Secretaria consultaSecretaria(Long idSecretaria) throws PrefeituraNegocioException {
		
		if (!secretariaRepo.existsById(idSecretaria)) {
			throw new PrefeituraNegocioException("A secretaria não existe!");
		}
		
		return secretariaRepo.findById(idSecretaria).get();
	}
	
	public ResponseDTO removeSecretaria(Long idSecretaria) throws PrefeituraNegocioException {
		
		if (!secretariaRepo.existsById(idSecretaria)) {
			throw new PrefeituraNegocioException("A secretaria não existe!");
		}
		
		secretariaRepo.deleteById(idSecretaria);
		
		return new ResponseDTO("A secretaria foi removida com sucesso!");
	}
	
	public ResponseDTO folhaDePagamento(Long idSecretaria) {
		
		Double somaFolha = servidorRepo.calculaFolhaPagamento(idSecretaria);
		
		return new ResponseDTO("Hoje a folha de pagamento da prefeitura somada tem o valor de R$ " + somaFolha);
	}
	
	public ResponseDTO custoProjetos(Long idSecretaria) {
		Double custoProjeto = projetoRepo.calculaSomaProjetos(idSecretaria);
		
		return new ResponseDTO("A soma dos custos de projeto dessa secretaria é R$ " + custoProjeto);
	}
	
	public ResponseDTO aporteProjeto(Long idSecretaria, AumentoDTO aumentoDto) throws PrefeituraNegocioException {
		
		Optional<Secretaria> secretariaOpt = secretariaRepo.findById(idSecretaria);
		
		if (secretariaOpt.isEmpty()) {
			throw new PrefeituraNegocioException("A secretaria não existe!");
		}
		
		Secretaria secretaria = secretariaOpt.get();
		
		secretaria.setOrcamentoProjeto(secretaria.getOrcamentoProjeto() + aumentoDto.getValorAumento());
		
		return new ResponseDTO("Aporte realizado com sucesso!");
	}
	
}
