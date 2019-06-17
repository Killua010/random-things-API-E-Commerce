package br.com.randomthings.strategy.standard;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StRegistration implements IStrategy<DomainEntity> {

	@Override
	public String execute(DomainEntity entity) {
		entity.setCreationDate(LocalDateTime.now());
		entity.setLastUpdate(LocalDateTime.now());
		entity.setStatus(true);
		return "";
	}

}
