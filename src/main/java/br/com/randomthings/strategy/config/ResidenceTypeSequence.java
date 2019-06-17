package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.domain.ResidenceType;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.residence_type.StResidenceTypeValidateExistence;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
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
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;

	@Bean("SAVE_RESIDENCETYPE")
	public Sequence<ResidenceType> saveResidenceType() {
		return new Sequence<ResidenceType>()
				.add(stResidenceTypeValidateExistence)
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_RESIDENCETYPE")
	public Sequence<ResidenceType> updateResidenceType() {
		return new Sequence<ResidenceType>()
				.add(stResidenceTypeValidateExistence)
				.add(stAdminAuthorization)
				.add(stLastUpdate);
	}
	
	@Bean("DELETE_RESIDENCETYPE")
	public Sequence<Activation> deleteResidenceType() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}

}
