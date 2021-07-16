package br.com.qm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.dto.CriaSecretariaDTO;
import br.com.qm.dto.SecretariaCriadaDTO;
import br.com.qm.entity.Projeto;
import br.com.qm.entity.Secretaria;
import br.com.qm.exception.PrefeituraNegocioException;
import br.com.qm.service.SecretariaService;

@RestController
@RequestMapping("/secretarias")
public class SecretariaController {
	
	@Autowired
	SecretariaService secretariaService;
	
	@PostMapping
	public SecretariaCriadaDTO criaSecretaria(@Valid @RequestBody CriaSecretariaDTO criaSecretaria /*receberia o DTO*/ ) throws PrefeituraNegocioException {
		return secretariaService.criaSecretaria(criaSecretaria);
	}
	
	@GetMapping
	public List<Secretaria> listarSecretarias() {
		return secretariaService.listarSecretarias();
	}
	
	@GetMapping(path = "/{idSecretaria}")
	public Secretaria consultaSecretaria (@PathVariable long idSecretaria) {
		// classe de servico
		return null;
	}
	
	@DeleteMapping( path ="/{idSecretaria}")
	public void removeSecretaria(@PathVariable long idSecretaria) {
		
	}
	
	@GetMapping(path = "/{idSecretaria}/folha-pagamento")
	public Double calculaFolhaPagamento(@PathVariable long idSecretaria) {
		return null;
	}
	
	@GetMapping(path = "/{idSecretaria}/custo-projeto")
	public Double calculaCustoProjeto(@PathVariable long idSecretaria) {
		return null;
	}
	
	@PutMapping(path = "/{idSecretaria}/aporte-projetos")
	public String realizaAporte(@PathVariable long idSecretaria, @Valid @RequestBody Projeto aumentoDto) {
		return null;
	}
//	POST
//	Criar Secretaria
//	Validar existencia de outra com a mesma pasta
//
//	GET
//	Listar Secretarias
//
//	GET /{idSecretaria}
//	BuscarSecretaria pelo Id
//
//	DELETE /{idSecretaria}
//	Remover Secreatria
//
//	GET /{idSecretaria}/folha-pagamento
//	Devolver a soma do salário de todos os funcionários dessa secretaria
//
//	GET /{idSecretaria}/custo-projeto
//	Devolver a soma dos custos de todos os projetos
//
//	PUT /{idSecretaria}/aporte-projetos
//	Aumentar orcamento de projetoss
//	receber um AumentoDTO{ "valorAumento": valor} no corpo

	
}
