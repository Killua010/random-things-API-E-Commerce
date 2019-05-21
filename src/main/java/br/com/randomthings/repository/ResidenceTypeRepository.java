package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.ResidenceType;

public interface ResidenceTypeRepository extends IRepository<ResidenceType, Long>, JpaRepository<ResidenceType, Long> {
	
	@Transactional(readOnly=true)
	Optional<ResidenceType> findByName(String name);

}
