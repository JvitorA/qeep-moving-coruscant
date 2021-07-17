package br.com.qm.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.qm.dto.CriaServidorDTO;
import br.com.qm.entity.Secretaria;
import br.com.qm.entity.Servidor;
import br.com.qm.enums.Pasta;
import br.com.qm.exception.PrefeituraNegocioException;
import br.com.qm.repository.SecretariaRepository;
import br.com.qm.repository.ServidorRepository;

@RunWith(MockitoJUnitRunner.class)
public class ServidorServiceTests {

	@Mock
	ServidorRepository servidorRepo;

	@Mock
	SecretariaRepository secretariaRepo;

	@InjectMocks
	ServidorService servidorService;

	@Test
	public void deveCriarUmServidor() throws PrefeituraNegocioException {
		CriaServidorDTO criaServidor = new CriaServidorDTO("João", "2221312", 10000, "Secretário");
		Secretaria secretaria = new Secretaria("Secretaria de Saúde", Pasta.EDUCACAO, 15000, 10);
		secretaria.setIdSecretaria(11L);

		Mockito.when(secretariaRepo.findById(11L)).thenReturn(Optional.of(secretaria));
		Mockito.when(servidorRepo.save(Mockito.any())).thenReturn(criaServidor.toEntity());

		Servidor servidorEsperado = criaServidor.toEntity();

		Servidor servidorRetornado = servidorService.criaServidor(11L, criaServidor);

		Assert.assertEquals(5000, secretaria.getOrcamentoFolha(), 0);
		Assert.assertEquals(servidorEsperado, servidorRetornado);
	}

	@Test
	public void naoDeveCriarUmServidorCasoASecretariaNaoExista() {
		CriaServidorDTO criaServidor = new CriaServidorDTO("João", "2221312", 10000, "Secretário");
		Optional<Secretaria> secretaria = Optional.empty();
		
		Mockito.when(secretariaRepo.findById(11L)).thenReturn(secretaria);
		String mensagemEsperada = "Erro ao criar servidor: Secretaria não existe.";
		String mensagemRetornada = null;
		
		try {
			servidorService.criaServidor(11L, criaServidor);
		} catch (PrefeituraNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemEsperada, mensagemRetornada);
	}
	
	@Test
	public void naoDeveCriarUmServidorCasoOrcamentoSejaInsuficiente() {
		CriaServidorDTO criaServidor = new CriaServidorDTO("João", "2221312", 10000, "Secretário");
		Secretaria secretaria = new Secretaria("Secretaria de Saúde", Pasta.EDUCACAO, 9000, 10);
		secretaria.setIdSecretaria(11L);

		Mockito.when(secretariaRepo.findById(11L)).thenReturn(Optional.of(secretaria));
		String mensagemEsperada = "Erro ao criar servidor: Não há orçamento para contratar o servidor.";
		String mensagemRetornada = null;
		
		try {
			servidorService.criaServidor(11L, criaServidor);
		} catch (PrefeituraNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemEsperada, mensagemRetornada);
		
	}

	@Test
	public void deveConsultarUmServidorComSucesso() throws PrefeituraNegocioException {
		Servidor servidor = new Servidor(11L, "João", "1234", 8000, "Secretario", null);
		
		Mockito.when(servidorRepo.findByIdServidorAndSecretariaIdSecretaria(11L, 12L)).thenReturn(Optional.of(servidor));
		
		Servidor servidorRetornado = servidorService.consultaServidor(12L, 11L);
		
		Assert.assertEquals(servidor, servidorRetornado);
	}
	
	@Test
	public void naoDeveRetornarUmServidorQuandoEleNaoExistir() {
		Optional<Servidor> servidor = Optional.empty();
		
		Mockito.when(servidorRepo.findByIdServidorAndSecretariaIdSecretaria(11L, 12L)).thenReturn(servidor);
		String mensagemEsperada = "Erro ao consultar servidor: Servidor não encontrado.";
		String mensagemRetornada = null;
		
		try {
			servidorService.consultaServidor(12L, 11L);
		} catch (PrefeituraNegocioException e) {
			mensagemRetornada = e.getMessage();
		}
		
		Assert.assertEquals(mensagemEsperada, mensagemRetornada);
	}
}
