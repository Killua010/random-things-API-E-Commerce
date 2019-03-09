package br.com.randomthings.command;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.utils.Result;

public interface ICommand {
	public Result execute(DomainEntity entity);
}
