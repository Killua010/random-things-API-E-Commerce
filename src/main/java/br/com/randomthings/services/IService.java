package br.com.randomthings.services;

import br.com.randomthings.domain.DomainEntity;

public interface IService <domain extends DomainEntity> {
	domain findById(Long id);
	domain save(domain domain);
	void deletar(Long id);
}
