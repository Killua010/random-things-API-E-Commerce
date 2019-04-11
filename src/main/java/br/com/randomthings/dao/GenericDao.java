package br.com.randomthings.dao;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.repository.IRepository;
import br.com.randomthings.services.IService;

@Service
@Transactional
public class GenericDao<entity extends DomainEntity> implements IDao<entity>{
	
	@Autowired
	private Map<String, IService<entity>> services;

	@Override
	public entity save(entity entity) {
		return searchRepository(entity).save((entity) entity);
	}

	@Override
	public List<entity> find(entity entity) {
		if(null != entity.getId()) {
			return Arrays.asList(searchRepository(entity).findById(entity.getId()));
		}
		return searchRepository(entity).findAll();
	}

	@Override
	public entity update(entity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(entity entity) {
		searchRepository(entity).delete(entity.getId());
	}
	
	private IService<entity> searchRepository(entity entity){
		for (Entry<String, IService<entity>> service : services.entrySet()) {
			if(service.getKey().toLowerCase()
					.equals(entity.getClass().getSimpleName().concat("ServiceImpl").toLowerCase())) {
				return service.getValue();
			}
		}
		return null;
	}
	
}
