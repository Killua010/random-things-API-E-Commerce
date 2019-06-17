package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.Activation;

@Repository
public interface ActivationRepository extends IRepository<Activation, Long>, JpaRepository<Activation, Long> {
	
}
