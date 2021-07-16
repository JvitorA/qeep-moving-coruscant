package br.com.qm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.entity.Projeto;

@Repository
public interface ProjetoRepository extends CrudRepository<Projeto, Long>{

	@Query("select sum(p.custo) from Projeto p where p.secretaria.idSecretaria = ?1")
	Double calculaSomaProjetos(Long idSecretaria);
	
	List<Projeto> findAllBySecretariaIdSecretariaAndConcluido(Long idSecretaria, Boolean concluido);
}
