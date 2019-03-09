package br.com.randomthings.dao;

import java.util.List;

import br.com.randomthings.domain.DomainEntity;

public interface IDao<entity extends DomainEntity> {
	
	public entity save(entity entity);
	public List<entity> find(entity entity);
	public entity update(entity entity);
	public void delete(entity entity);
	
}
