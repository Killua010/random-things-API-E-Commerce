package br.com.randomthings.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.repository.IRepository;

@Service
@Transactional
public class GenericDao<entity extends DomainEntity> implements IDao<entity>{
	
	@Autowired
	private Map<String, IRepository<entity>> repositories;

	@Override
	public entity save(entity entity) {
		return searchRepository(entity).save((entity) entity);
	}

	@Override
	public List<entity> find(entity entity) {
		
		if(null != entity.getId()) {
			return Arrays.asList(searchRepository(entity).findById(entity.getId()).orElse(null));
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
		searchRepository(entity).delete(entity);
	}
	
	private IRepository<entity> searchRepository(entity entity){
		for (Entry<String, IRepository<entity>> repository : repositories.entrySet()) {
			if(repository.getKey().toLowerCase()
					.equals(entity.getClass().getSimpleName().concat("Repository").toLowerCase())) {
				return repository.getValue();
			}
		}
		return null;
	}
	
}
