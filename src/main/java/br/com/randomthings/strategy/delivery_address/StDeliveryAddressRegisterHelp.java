package br.com.randomthings.strategy.delivery_address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.City;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.repository.CityRepository;
import br.com.randomthings.repository.ResidenceTypeRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StDeliveryAddressRegisterHelp implements IStrategy<DeliveryAddress>{

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ResidenceTypeRepository residenceTypeRepository;
	
	@Override
	public String execute(DeliveryAddress entity) {
			
		Optional<City> cityOptional = cityRepository.findByIdAndStatusTrue(entity.getCity().getId());
		if(!cityOptional.isPresent()) {
			return "A cidade com id = " + entity.getCity().getId() + " é inexistente";
		}
		
		entity.setCity(cityOptional.get());
		
		Optional<ResidenceType> residenceTypeOptional = residenceTypeRepository.findByIdAndStatusTrue(entity.getResidenceType().getId());
		if(!residenceTypeOptional.isPresent()) {
			return "O tipo de residencia com id = " + entity.getCity().getId() + " é inexistente";
		}
		
		entity.setResidenceType(residenceTypeOptional.get());
		return "";
	}

}
