package br.com.qm.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.entity.Projeto;

@RestController
@RequestMapping(path = "/secretarias/{idSecretaria}/projetos")
public class ProjetoController {

	@PostMapping
	public void cadastraProjeto(@PathVariable long idSecretaria, @Valid @RequestBody Projeto projeto) {
		// classe de servico
	}
	
	@GetMapping
	public void listaProjetos(@PathVariable long idSecretaria) {
		// classe de servico
	}
	
	@GetMapping(path = "/{idProjeto}")
	public void consultaProjeto(@PathVariable long idSecretaria, @PathVariable long idProjeto) {
		// classe 
	}
	
	@GetMapping(path = "/concluidos")
	public void listaProjetosConcluidos(@PathVariable long idSecretaria) {
		
	}
	
	@GetMapping(path = "/execucao") 
	public void listaProjetosEmExecucao(@PathVariable long idSecretaria) {
		
	}
	
	@GetMapping(path = "/conclusao-mes/{mes}")
	public void listaPrevisa(@PathVariable long idSecretaria, @PathVariable int mes) {
		
	}
	
	@PutMapping(path = "/{idProjeto}")
	public void finalizaProjeto(@PathVariable long idSecretaria, @PathVariable long idProjeto) {
		
	}
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
//	Listar projetos que tem data de previsão para o mês (numérico)
//
//	PUT /{idSecretaria}/projetos/{idProjeto}
//	Alterar a conclusão para true e salvar a data de conclusão como a data da chamada à este endpoint
	
}
