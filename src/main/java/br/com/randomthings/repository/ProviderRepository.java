package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.Provider;

public interface ProviderRepository extends IRepository<Provider, Long>, JpaRepository<Provider, Long> {
	
	Optional<Provider> findByName(String name);
	
}
