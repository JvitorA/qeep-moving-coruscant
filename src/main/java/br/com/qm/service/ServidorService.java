package br.com.qm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.dto.CriaServidorDTO;
import br.com.qm.dto.ResponseDTO;
import br.com.qm.entity.Secretaria;
import br.com.qm.entity.Servidor;
import br.com.qm.exception.PrefeituraNegocioException;
import br.com.qm.repository.SecretariaRepository;
import br.com.qm.repository.ServidorRepository;

@Service
public class ServidorService {

//	POST /secretarias/{idSecretaria}/servidores
//	Criar Servidor
//	Validar se a secretaria tem orcamento suficiente para pagar o salário,
//	Alterar o orcamentoFolha para se adequar ao salário do servidor
//
//	GET /secretarias/{idSecretaria}/servidores
//	Listar servidores por secretaria
//
//	GET /secretarias/{idSecretaria}/servidores/{idServidor}
//	Consultar um servidor
//
//	DELETE /secretarias/{idSecretaria}/servidores/{idServidor}
//	Remover um servidor
//	alterar o orcamentoFolha para remover o gasto com esse servidor
	@Autowired
	ServidorRepository servidorRepo;
	
	@Autowired
	SecretariaRepository secretariaRepo;
	
	
	public Servidor criaServidor(long idSecretaria, CriaServidorDTO criaServidor) throws PrefeituraNegocioException {
		
		Optional<Secretaria> secretariaOpt = secretariaRepo.findById(idSecretaria);
		
		if (secretariaOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao criar servidor: Secretaria não existe.");
		}
		
		Secretaria secretaria = secretariaOpt.get();
		
		double orcamentoAtual = secretaria.getOrcamentoFolha();
		double salarioServidor = criaServidor.getSalario();
		
		if (orcamentoAtual < salarioServidor) {
			throw new PrefeituraNegocioException("Erro ao criar servidor: Não há orçamento para contratar o servidor.");
		}
		
		Servidor servidor = criaServidor.toEntity();
		servidor.setSecretaria(secretaria);

		secretaria.setOrcamentoFolha(orcamentoAtual - salarioServidor);
		secretariaRepo.save(secretaria);
		
		return servidorRepo.save(servidor);
	}
	
	public List<Servidor> listaServidores(long idSecretaria) {
		
		return servidorRepo.findAllBySecretariaIdSecretaria(idSecretaria);
		
	}
	
	public Servidor consultaServidor(long idSecretaria, long idServidor) throws PrefeituraNegocioException {
		
		Optional<Servidor> servidorOpt = servidorRepo.findByIdServidorAndSecretariaIdSecretaria(idServidor, idSecretaria);
		
		if (servidorOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao consultar servidor: Servidor não encontrado.");
		}
		
		return servidorOpt.get();
	}
	
	public ResponseDTO removeServidor(long idSecretaria, long idServidor) throws PrefeituraNegocioException {
		
		Optional<Servidor> servidorOpt = servidorRepo.findByIdServidorAndSecretariaIdSecretaria(idServidor, idSecretaria);
		
		if (servidorOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao remover servidor: Servidor não encontrado.");
		}
		
		Optional<Secretaria> secretariaOpt = secretariaRepo.findById(idSecretaria);
		
		if (secretariaOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao remover servidor: Secretaria não encontrada.");
		}
		
		Secretaria secretaria = secretariaOpt.get();
		secretaria.setOrcamentoFolha(secretaria.getOrcamentoFolha() + servidorOpt.get().getSalario());
		
		secretariaRepo.save(secretaria);
		servidorRepo.deleteById(idServidor);
		
		
		return new ResponseDTO("Servidor foi removido com sucesso!");
	}
}
