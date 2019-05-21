package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.State;

@Repository
public interface StateRepository extends IRepository<State, Long>, JpaRepository<State, Long>{

}
