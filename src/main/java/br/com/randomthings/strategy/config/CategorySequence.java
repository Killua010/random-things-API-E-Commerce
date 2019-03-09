package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Category;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.category.StCategoryValidateExistence;
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

	@Bean("SAVE_CATEGORY")
	public Sequence<Category> saveCategory() {
		return new Sequence<Category>()
				.add(stCategoryValidateExistence)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_CATEGORY")
	public Sequence<Category> updateCategory() {
		return new Sequence<Category>()
				.add(stCategoryValidateExistence)
				.add(stLastUpdate);
	}

}
