package br.com.randomthings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.PricingGroup;

@Repository
public interface PricingGroupRepository extends IRepository<PricingGroup, Long>, JpaRepository<PricingGroup, Long>{
	
	@Transactional(readOnly=true)
	Optional<PricingGroup> findByName(String name);

}
