package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.DomainEntity;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.category.StCategoryValidateExistence;
import br.com.randomthings.strategy.standard.StAdminAuthorization;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class CategorySequence {

	@Autowired
	StCategoryValidateExistence stCategoryValidateExistence;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;
	
	@Autowired
	StAdminAuthorization stAdminAuthorization;

	@Bean("SAVE_CATEGORY")
	public Sequence<Category> saveCategory() {
		return new Sequence<Category>()
				.add(stCategoryValidateExistence)
				.add(stAdminAuthorization)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_CATEGORY")
	public Sequence<Category> updateCategory() {
		return new Sequence<Category>()
				.add(stCategoryValidateExistence)
				.add(stAdminAuthorization)
				.add(stLastUpdate);
	}

	@Bean("DELETE_CATEGORY")
	public Sequence<Activation> deleteCategory() {
		return new Sequence<DomainEntity>()
				.add(stAdminAuthorization);
	}

}
