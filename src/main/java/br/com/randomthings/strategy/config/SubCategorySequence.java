package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;
import br.com.randomthings.strategy.subcategory.StSubCategoryFindCategory;
import br.com.randomthings.strategy.subcategory.StSubCategoryValidateExistence;

@Configuration
public class SubCategorySequence {

	@Autowired
	StSubCategoryValidateExistence stSubCategoryValidateExistence;
	
	@Autowired
	StSubCategoryFindCategory stSubCategoryFindCategory;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_SUBCATEGORY")
	public Sequence<SubCategory> saveSubCategory() {
		return new Sequence<SubCategory>()
				.add(stSubCategoryFindCategory)
				.add(stSubCategoryValidateExistence)
				.add(stRegistration);
	}
	
	@Bean("UPDATE_SUBCATEGORY")
	public Sequence<SubCategory> updateSubCategory() {
		return new Sequence<SubCategory>()
				.add(stSubCategoryFindCategory)
				.add(stSubCategoryValidateExistence)
				.add(stLastUpdate);
	}

}
