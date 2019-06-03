package br.com.randomthings.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.services.IService;

@Service
@Transactional
public class GenericServiceDao<entity extends DomainEntity> implements IDao<entity>{
	
	@Autowired
	private Map<String, IService> services;

	@Override
	public entity save(entity entity) {
		return (entity) searchRepository(entity).save(entity);
	}

	@Override
	public List<entity> find(entity entity) {
		if(null != entity.getId()) {
			return (List<entity>) Arrays.asList(searchRepository(entity).findByIdAndStatusTrue(entity.getId()));
		}
		return searchRepository(entity).findAllByStatusTrue();
	}

	@Override
	public entity update(entity entity) {
		return (entity) searchRepository(entity).update(entity);
	}

	@Override
	public void delete(entity entity) {
		searchRepository(entity).deleteById(entity.getId());
	}
	
	private IService searchRepository(entity entity){
		for (Entry<String, IService> service : services.entrySet()) {
			if(service.getKey().toLowerCase()
					.equals(entity.getClass().getSimpleName().concat("ServiceImpl").toLowerCase())) {
				return service.getValue();
			}
		}
		return null;
	}
	
}
