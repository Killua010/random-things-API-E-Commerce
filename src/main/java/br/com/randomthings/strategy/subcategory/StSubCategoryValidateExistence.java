package br.com.randomthings.strategy.subcategory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.repository.SubCategoryRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StSubCategoryValidateExistence implements IStrategy<SubCategory> {

	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Override
	public String execute(SubCategory entity) {
		Optional<SubCategory> optionalSubCategory = subCategoryRepository.findByNameAndCategory(entity.getName(), ((SubCategory)entity).getCategory());
		
		if(optionalSubCategory.isPresent()) {
			if(!optionalSubCategory.get().getId().equals(entity.getId())) {
				return "SubCategoria já cadastrada no sistema";
			}
		}
		return "";
	}

}
