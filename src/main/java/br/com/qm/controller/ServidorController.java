package br.com.qm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qm.dto.CriaServidorDTO;
import br.com.qm.dto.ResponseDTO;
import br.com.qm.entity.Servidor;
import br.com.qm.exception.PrefeituraNegocioException;
import br.com.qm.service.ServidorService;

@RestController
@RequestMapping(path = "/secretarias/{idSecretaria}/servidores")
public class ServidorController {

	@Autowired
	ServidorService servidorService;
	
	@PostMapping
	public Servidor cadastraServidor(@PathVariable long idSecretaria, @Valid @RequestBody CriaServidorDTO servidor) throws PrefeituraNegocioException {
		return servidorService.criaServidor(idSecretaria, servidor);
	}
	
	@GetMapping
	public List<Servidor> listarServidores(@PathVariable long idSecretaria) {
		return servidorService.listaServidores(idSecretaria);
	}
	
	@GetMapping(path = "/{idServidor}")
	public Servidor consultaServidor(@PathVariable long idSecretaria, @PathVariable long idServidor) throws PrefeituraNegocioException {
		return servidorService.consultaServidor(idSecretaria, idServidor);
	}
	
	@DeleteMapping(path = "/{idServidor}")
	public ResponseDTO removeServidor(@PathVariable long idSecretaria, @PathVariable long idServidor) throws PrefeituraNegocioException {
		return servidorService.removeServidor(idSecretaria, idServidor);
	}
	
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

	
}
