package br.com.randomthings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.PricingGroup;

@Repository
public interface PricingGroupRepository extends RepositoryImpl<PricingGroup>, IRepository<PricingGroup>{
	
	@Transactional(readOnly=true)
	Optional<PricingGroup> findByName(String name);
	
	List<PricingGroup> findByStatusTrue();

}
