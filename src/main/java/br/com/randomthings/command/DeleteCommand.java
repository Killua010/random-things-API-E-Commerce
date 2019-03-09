package br.com.randomthings.command;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.utils.Result;

@Service
public class DeleteCommand extends AbstractCommand {

	@Override
	public Result execute(DomainEntity entity) {
		return facade.delete(entity);
	}

}
