package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.TechnicalField;

@Repository
public interface TechnicalFieldRepository extends RepositoryImpl<TechnicalField>, IRepository<TechnicalField>{

	@Transactional(readOnly=true)
	Optional<TechnicalField> findByName(String name);

}
