package br.com.randomthings.services;

import java.util.List;

import br.com.randomthings.domain.DomainEntity;

public interface IService <domain extends DomainEntity> {
	domain findById(Long id);
	domain save(domain domain);
	void delete(Long id);
	List<domain> findAll();
}
