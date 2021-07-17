package br.com.qm.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.entity.Projeto;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto, Long>{

	@Query("select sum(p.custo) from Projeto p where p.secretaria.idSecretaria = ?1")
	Double calculaSomaProjetos(Long idSecretaria);

	List<Projeto> findAllBySecretariaIdSecretaria(Long idSecretaria);
	
	Optional<Projeto> findBySecretariaIdSecretariaAndIdProjeto(Long idSecretaria, Long idProjeto);
	
	List<Projeto> findAllBySecretariaIdSecretariaAndConcluido(Long idSecretaria, Boolean concluido);
	
	List<Projeto> findAllBySecretariaIdSecretariaAndDataPrevistaConclusaoBetweenAndConcluido(Long idSecretaria, LocalDate inicioDoMes, LocalDate fimDoMes, Boolean concluido);

	@Query("select p from Projeto p where p.secretaria.idSecretaria = ?1 and month(p.dataPrevistaConclusao) = ?2 and p.concluido = false")
	List<Projeto> findAllMes(Long idSecretaria, int mes);
}
