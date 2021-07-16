package br.com.qm.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.qm.dto.CriaSecretariaDTO;
import br.com.qm.entity.Secretaria;
import br.com.qm.enums.Pasta;

@Repository
public interface SecretariaRepository extends CrudRepository<Secretaria, Long>{

	Secretaria findFirstByPasta(Pasta pasta);
	
	Secretaria save(CriaSecretariaDTO cria);
}
