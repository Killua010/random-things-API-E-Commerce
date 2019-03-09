package br.com.randomthings.strategy.pricing_group;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.PricingGroup;
import br.com.randomthings.repository.PricingGroupRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StPricingGroupValidateExistence implements IStrategy<PricingGroup> {

	@Autowired
	private PricingGroupRepository pricingRepository;
	
	@Override
	public String execute(PricingGroup entity) {
		Optional<PricingGroup> optionalPricing = pricingRepository.findByName(entity.getName());
		
		if(optionalPricing.isPresent()) {
			if(!optionalPricing.get().getId().equals(entity.getId())) {
				return "Grupo já cadastrada no sistema";
			}
		}
		return "";
	}



}
