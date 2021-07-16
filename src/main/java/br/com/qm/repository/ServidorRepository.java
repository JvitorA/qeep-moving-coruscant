package br.com.qm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.entity.Servidor;

@Repository
public interface ServidorRepository extends CrudRepository<Servidor, Long> {

	List<Servidor> findAllBySecretariaIdSecretaria(Long idSecretaria);

	@Query("select s from Servidor s where s.secretaria.idSecretaria = ?1")
	List<Servidor> findDoCaio(Long idSecretaria);
	
	Optional<Servidor> findByIdServidorAndSecretariaIdSecretaria(long idServidor, long idSecretaria);
	
	@Query("select sum(s.salario) from Servidor s where s.secretaria.idSecretaria = ?1")
	Double calculaFolhaPagamento(Long idSecretaria);
}
