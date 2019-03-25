package br.com.randomthings.repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import br.com.randomthings.domain.DomainEntity;

public interface RepositoryImpl <domain extends DomainEntity> {
	List<domain> findByStatusTrue();
	
//	Page<domain> findByStatusTrue(Pageable pageable);
	
	Optional<domain> findByIdAndStatusTrue(Long id);
}
