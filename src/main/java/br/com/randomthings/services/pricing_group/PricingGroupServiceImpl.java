package br.com.randomthings.services.pricing_group;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.PricingGroup;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.PricingGroupRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class PricingGroupServiceImpl extends AbstractService<PricingGroup, Long> implements PricingGroupService {
	@Autowired
	private PricingGroupRepository pricingGroupRepository;
	
	public PricingGroupServiceImpl(PricingGroupRepository dao) {
		super(dao);
	}
	
	@Override
	@Transactional 
	public void deleteById(Long id) {
		PricingGroup pricingGroup = findById(id);
		pricingGroup.setStatus(false);
		pricingGroup.setLastUpdate(LocalDateTime.now());
		pricingGroupRepository.save(pricingGroup);
	}
	
}
