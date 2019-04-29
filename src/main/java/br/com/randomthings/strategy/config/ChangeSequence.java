package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class ChangeSequence {
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_CHANGE")
	public Sequence<Change> saveChange() {
		return new Sequence<DomainEntity>()
				.add(stRegistration);
	}
//	
//	@Bean("UPDATE_CATEGORY")
//	public Sequence<Category> updateCategory() {
//		return new Sequence<Category>()
//				.add(stCategoryValidateExistence)
//				.add(stLastUpdate);
//	}

}
