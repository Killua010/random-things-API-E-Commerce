package br.com.randomthings.strategy;

import br.com.randomthings.domain.DomainEntity;

public interface IStrategy<entity extends DomainEntity> {
	public String execute(entity entity);
}
