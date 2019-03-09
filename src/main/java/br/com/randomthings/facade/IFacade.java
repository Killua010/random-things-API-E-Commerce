package br.com.randomthings.facade;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.utils.Result;

public interface IFacade<entity extends DomainEntity> {
	
	public Result save(entity entity);
	public Result find(entity entity);
	public Result update(entity entity);
	public Result delete(entity entity);

}
