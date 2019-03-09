package br.com.randomthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.randomthings.domain.DomainEntity;

public interface IRepository <entity extends DomainEntity> extends JpaRepository <entity, Long> {

}
