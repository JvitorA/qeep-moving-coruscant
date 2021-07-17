package br.com.qm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.dto.CriaProjetoDTO;
import br.com.qm.entity.Projeto;
import br.com.qm.exception.PrefeituraNegocioException;
import br.com.qm.service.ProjetoService;

@RestController
@RequestMapping(path = "/secretarias/{idSecretaria}/projetos")
public class ProjetoController {

	@Autowired
	ProjetoService projetoService;
	
	@PostMapping
	public Projeto criaProjeto(@PathVariable long idSecretaria, @Valid @RequestBody CriaProjetoDTO projeto) throws PrefeituraNegocioException {
		return projetoService.criaProjeto(idSecretaria, projeto);
	}
	
	@GetMapping
	public List<Projeto> listarProjetos(@PathVariable long idSecretaria) {
		return projetoService.listarProjetos(idSecretaria);
	}
	
	@GetMapping(path = "/{idProjeto}")
	public Projeto consultaProjeto(@PathVariable long idSecretaria, @PathVariable long idProjeto) throws PrefeituraNegocioException {
		return projetoService.consultaProjeto(idSecretaria, idProjeto);
	}
	
	@GetMapping(path = "/concluidos")
	public List<Projeto> listarProjetosConcluidos(@PathVariable long idSecretaria) {
		return projetoService.listarProjetosConcluidos(idSecretaria);
	}
	
	@GetMapping(path = "/execucao") 
	public List<Projeto> listarProjetosEmExecucao(@PathVariable long idSecretaria) {
		return projetoService.listarProjetosEmExecucao(idSecretaria);
	}
	
	@GetMapping(path = "/conclusao-mes/{mes}")
	public List<Projeto> listarPrevisao(@PathVariable long idSecretaria, @PathVariable int mes) {
		return projetoService.listarProjetosComPrevisao(idSecretaria, mes);
	}
	
	@PutMapping(path = "/{idProjeto}")
	public Projeto concluirProjeto(@PathVariable long idSecretaria, @PathVariable long idProjeto) throws PrefeituraNegocioException {
		return projetoService.concluirProjeto(idSecretaria, idProjeto);
	}
	
}
