package br.com.randomthings.services.pricing_group;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.PricingGroup;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.PricingGroupRepository;

@Service
public class PricingGroupServiceImpl implements PricingGroupService {
	
	@Autowired
	private PricingGroupRepository pricingGroupRepository;
	
	public PricingGroup findById(Long id) {		
		return pricingGroupRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + PricingGroup.class.getSimpleName()));
	}

	@Override
	@Transactional 
	public PricingGroup save(PricingGroup domain) {
		domain = pricingGroupRepository.save(domain);
		return domain;
	}

	@Override
	@Transactional 
	public void delete(Long id) {
		PricingGroup pricingGroup = findById(id);
		pricingGroup.setStatus(false);
		pricingGroup.setLastUpdate(LocalDateTime.now());
		pricingGroupRepository.save(pricingGroup);
	}

	@Override
	public List<PricingGroup> findAll() {
		return pricingGroupRepository.findByStatusTrue(); 
	}
	
}
