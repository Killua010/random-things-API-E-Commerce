package br.com.randomthings.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.dao.GenericServiceDao;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.utils.Result;

@Service
public class Facade<entity extends DomainEntity> implements IFacade<entity> {
	
	@Autowired
	private Map<String, Sequence<entity>> listSequence = new HashMap<String, Sequence<entity>>();
	
	@Autowired
	private GenericServiceDao<DomainEntity> serviceDao;

	private Result result;

	@Override
	public Result save(entity entity) {
		result = new Result();

		StringBuilder errors = runStrategys(entity, "Save");

		if (errors.length() == 0) {
			result.getResultEntities().add(serviceDao.save(entity));
		} else {
			throw new StrategyValidation(errors);
		}
		return result;
	}

	@Override
	public Result find(entity entity) {
		result = new Result();
		result.setResultEntities(serviceDao.find(entity));		
		return result;
	}

	@Override
	public Result update(entity entity) {
		result = new Result();
		
		StringBuilder errors = runStrategys(entity, "Update");

		if (errors.length() == 0) {
			result.getResultEntities().add(serviceDao.update(entity));
		} else {
			throw new StrategyValidation(errors);
		}
		return result;
	}

	@Override
	public Result delete(entity entity) {
		result = new Result();
		serviceDao.delete(entity);
		return result;
	}

	private StringBuilder runStrategys(entity entity, String operation) {
		StringBuilder errors = new StringBuilder();
		
		listSequence
			.get(operation.toUpperCase().concat("_").concat(entity.getClass().getSimpleName()).toUpperCase())
		.getIStrategies().forEach(iStr -> errors.append(iStr.execute((entity) entity)));

		return errors;
	}

}
