package br.com.qm.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.qm.dto.CriaProjetoDTO;
import br.com.qm.entity.Projeto;
import br.com.qm.entity.Secretaria;
import br.com.qm.exception.PrefeituraNegocioException;
import br.com.qm.repository.ProjetoRepository;
import br.com.qm.repository.SecretariaRepository;

@Service
public class ProjetoService {

//	POST /secretarias/{idSecretaria}/projetos
//	Criar Projeto
//	Validar se a secretaria tem orcamento suficiente para pagar o projeto,
//	Alterar o orcamentoFolha para se adequar ao custo do projeto,
//	Criar um DTO para não receber o id, a data de conclusao, concluido
//
//	GET /secretarias/{idSecretaria}/projetos
//	Listar projetos por secretaria
//
//	GET /{idSecretaria}/projetos/{idProjeto}
//	Consultar projeto
//
//	GET /{idSecretaria}/projetos/concluidos
//	Listar projetos concluidos
//
//	GET /{idSecretaria}/projetos/execucao
//	Listar projetos em execução
//
//	GET /{idSecretaria}/projetos/conclusao-mes/{mes}
//	Listar projetos que tem data de previsão para o mês do ano atual(numérico)
//
//	PUT /{idSecretaria}/projetos/{idProjeto}
//	Alterar a conclusão para true e salvar a data de conclusão como a data da chamada à este endpoint.
	@Autowired
	ProjetoRepository projetoRepo;

	@Autowired
	SecretariaRepository secretariaRepo;

	public Projeto criaProjeto(long idSecretaria, CriaProjetoDTO criaProjetoDto) throws PrefeituraNegocioException {

		if (criaProjetoDto.getDataPrevistaConclusao().isBefore(LocalDate.now())) {
			throw new PrefeituraNegocioException(
					"Erro ao criar um projeto: Data de previsão deve ser depois da data de hoje.");
		}

		Optional<Secretaria> secretariaOpt = secretariaRepo.findById(idSecretaria);

		if (secretariaOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao criar um projeto: Secretaria inexistente.");
		}

		Secretaria secretaria = secretariaOpt.get();

		double orcamentoAtual = secretaria.getOrcamentoProjeto();
		double custoProjeto = criaProjetoDto.getCusto();

		if (custoProjeto > orcamentoAtual) {
			throw new PrefeituraNegocioException(
					"Erro ao criar um projeto: Orçamento é insuficiente, solicite um aporte!");
		}

		Projeto projeto = criaProjetoDto.toEntity();

		secretaria.setOrcamentoProjeto(orcamentoAtual - custoProjeto);
		projeto.setSecretaria(secretaria);

		secretariaRepo.save(secretaria);
		return projetoRepo.save(projeto);
	}

	public List<Projeto> listarProjetos(long idSecretaria) {

		return projetoRepo.findAllBySecretariaIdSecretaria(idSecretaria);

	}

	public Projeto consultaProjeto(long idSecretaria, long idProjeto) throws PrefeituraNegocioException {

		Optional<Projeto> projetoOpt = projetoRepo.findBySecretariaIdSecretariaAndIdProjeto(idSecretaria, idProjeto);

		if (projetoOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao consultar um projeto: O projeto não existe.");
		}

		return projetoOpt.get();
	}

	public List<Projeto> listarProjetosConcluidos(long idSecretaria) {

		return projetoRepo.findAllBySecretariaIdSecretariaAndConcluido(idSecretaria, true);

	}

	public List<Projeto> listarProjetosEmExecucao(long idSecretaria) {

		return projetoRepo.findAllBySecretariaIdSecretariaAndConcluido(idSecretaria, false);

	}

	public List<Projeto> listarProjetosComPrevisao(long idSecretaria, int mes) {

		
//		return projetoRepo.findAllMes(idSecretaria, mes);
		int anoAtual = LocalDate.now().getYear();

		LocalDate dataInicio = LocalDate.of(anoAtual, mes, 1);
		LocalDate dataFim = LocalDate.of(anoAtual, mes, dataInicio.lengthOfMonth());

		return projetoRepo.findAllBySecretariaIdSecretariaAndDataPrevistaConclusaoBetweenAndConcluido(idSecretaria, dataInicio,
				dataFim, false);
	}

	public Projeto concluirProjeto(long idSecretaria, long idProjeto) throws PrefeituraNegocioException {

		Optional<Projeto> projetoOpt = projetoRepo.findBySecretariaIdSecretariaAndIdProjeto(idSecretaria, idProjeto);

		if (projetoOpt.isEmpty()) {
			throw new PrefeituraNegocioException("Erro ao concluir um projeto: O projeto não existe.");
		}

		Projeto projeto = projetoOpt.get();

		if (projeto.isConcluido()) {
			throw new PrefeituraNegocioException("Erro ao concluir um projeto: O projeto já foi concluído.");
		}

		projeto.setConcluido(true);
		projeto.setDataConclusao(LocalDate.now());

		return projetoRepo.save(projeto);
	}
}
