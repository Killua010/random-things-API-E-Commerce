package br.com.randomthings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Entity;

public interface IRepository <entity extends DomainEntity, key> {
	List<entity> findByStatusTrue();
	
	List<entity> findByStatusFalse();
	
	Optional<entity> findByIdAndStatusTrue(key id);
}
