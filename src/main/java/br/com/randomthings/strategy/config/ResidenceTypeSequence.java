package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.residence_type.StResidenceTypeValidateExistence;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class ResidenceTypeSequence {

	@Autowired
	StResidenceTypeValidateExistence stResidenceTypeValidateExistence;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_RESIDENCETYPE")
	public Sequence<Category> saveCategory() {
		return new Sequence<ResidenceType>()
				.add(stResidenceTypeValidateExistence)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_RESIDENCETYPE")
	public Sequence<Category> updateCategory() {
		return new Sequence<ResidenceType>()
				.add(stResidenceTypeValidateExistence)
				.add(stLastUpdate);
	}

}
