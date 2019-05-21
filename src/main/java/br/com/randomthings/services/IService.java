package br.com.randomthings.services;

import java.util.List;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.Entity;

public interface IService <domain extends DomainEntity, key> {
	domain findById(key id);
	domain findByIdAndStatusTrue(key id);
	domain save(domain domain);
	domain update(domain domain);
	void deleteById(key id);
	List<domain> findAllByStatusTrue();
	List<domain> findAll();
}
