package br.com.randomthings.strategy.residence_type;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.repository.CategoryRepository;
import br.com.randomthings.repository.ResidenceTypeRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StResidenceTypeValidateExistence implements IStrategy<ResidenceType> {

	@Autowired
	private ResidenceTypeRepository residenceTypeRepository;
	
	@Override
	public String execute(ResidenceType residenceType) {
		Optional<ResidenceType> optionalResidenceType = residenceTypeRepository.findByName(residenceType.getName());
		
		if(optionalResidenceType.isPresent()) {
			if(!optionalResidenceType.get().getId().equals(residenceType.getId())) {
				return "Tipo de residencia já cadastrada no sistema";
			}
		}
		return "";
	}



}
