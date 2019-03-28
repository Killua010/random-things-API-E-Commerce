package br.com.randomthings.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.Sequence;

public abstract class ExecuteStrategys<entity extends DomainEntity> {
	
	@Autowired
	private Map<String, Sequence<entity>> listSequence = new HashMap<String, Sequence<entity>>();
	
	protected StringBuilder runStrategys(entity entity, String operation) {
		StringBuilder errors = new StringBuilder();
		
		listSequence
			.get(operation.toUpperCase().concat("_").concat(entity.getClass().getSimpleName()).toUpperCase())
		.getIStrategies().forEach(iStr -> errors.append(iStr.execute((entity) entity)));

		return errors;
	}
}
