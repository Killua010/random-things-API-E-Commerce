package br.com.randomthings.facade;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.dao.GenericDao;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.utils.Result;

@Service
public class Facade<entity extends DomainEntity> implements IFacade<entity> {
	
	@Autowired
	private Map<String, Sequence<entity>> listNavigations = new HashMap<String, Sequence<entity>>();
	
	@Autowired
	private GenericDao<DomainEntity> dao;

	private Result result;

	@Override
	public Result save(entity entity) {
		
		result = new Result();

		StringBuilder errors = runStrategys(entity, "Save");

		if (errors.length() == 0) {
			result.getResultEntities().add(dao.save(entity));
		} else {
			result.setResponse(errors);
		}
		return result;

	}

	@Override
	public Result find(entity entity) {
		result = new Result();
		result.setResultEntities(dao.find(entity));
		if (null != entity.getId() && (result.getResultEntities().size() == 0 || null == result.getResultEntities().get(0))) {
			result.setResponse(new StringBuilder("Entidade não encontrada"));	
		}
		
		return result;
	}

	@Override
	public Result update(entity entity) {
		result = new Result();

		DomainEntity newEntity = (DomainEntity) dao.find(entity).get(0);
		entity.setStatus((null == entity.getStatus()) ? newEntity.getStatus() : entity.getStatus());
		entity.setCreationDate(newEntity.getCreationDate());;
		
		StringBuilder errors = runStrategys(entity, "Update");

		if (errors.length() == 0) {
			result.getResultEntities().add(dao.save(entity));
		} else {
			result.getResultEntities().add(entity);
			result.setResponse(errors);
		}
		return result;
	}

	@Override
	public Result delete(entity entity) {
		result = new Result();
		dao.delete(entity);
		return result;
	}

	protected StringBuilder runStrategys(entity entity, String operation) {
		StringBuilder errors = new StringBuilder();
		
		listNavigations
			.get(operation.toUpperCase().concat("_").concat(entity.getClass().getSimpleName()).toUpperCase())
		.getIStrategies().forEach(iStr -> errors.append(iStr.execute((entity) entity)));

//		listNavigations.entrySet().forEach(strategy -> {
//			if(strategy.getKey().toLowerCase()
//					.equals(operation.concat("_").concat(entity.getClass().getSimpleName()).toLowerCase())) {
//				strategy.getValue().getIStrategies().forEach(iStr -> errors.append(iStr.execute((entity) entity)));
//			}
//		});

		return errors;
	}

}
