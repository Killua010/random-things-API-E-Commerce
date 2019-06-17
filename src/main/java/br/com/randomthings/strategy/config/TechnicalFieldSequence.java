package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;
import br.com.randomthings.strategy.technical_field.StTechnicalFieldValidateExistence;

@Configuration
public class TechnicalFieldSequence {

	@Autowired
	StTechnicalFieldValidateExistence stTechnicalFieldValidateExistence;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_TECHNICALFIELD")
	public Sequence<TechnicalField> saveTechnicalField() {
		return new Sequence<TechnicalField>()
				.add(stTechnicalFieldValidateExistence)
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_TECHNICALFIELD")
	public Sequence<TechnicalField> updateTechnicalField() {
		return new Sequence<TechnicalField>()
				.add(stTechnicalFieldValidateExistence)
				.add(stAdminAuthorization)
				.add(stLastUpdate);
	}
	
	@Bean("FIND_TECHNICALFIELD")
	public Sequence<TechnicalField> findTechnicalField() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}
	
	@Bean("DELETE_TECHNICALFIELD")
	public Sequence<TechnicalField> deleteTechnicalField() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}

}
