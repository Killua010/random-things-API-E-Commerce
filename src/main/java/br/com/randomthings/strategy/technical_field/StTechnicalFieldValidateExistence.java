package br.com.randomthings.strategy.technical_field;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.repository.TechnicalFieldRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StTechnicalFieldValidateExistence implements IStrategy<TechnicalField> {

	@Autowired
	private TechnicalFieldRepository technicalFieldRepository;
	
	@Override
	public String execute(TechnicalField entity) {
		Optional<TechnicalField> optionalTechnicalField = technicalFieldRepository.findByName(entity.getName());
		
		if(optionalTechnicalField.isPresent()) {
			if(!optionalTechnicalField.get().getId().equals(entity.getId())) {
				return "Campo já cadastrada no sistema";
			}
		}
		return "";
	}

}
