package br.com.randomthings.strategy.subcategory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.repository.CategoryRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StSubCategoryFindCategory implements IStrategy<SubCategory> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public String execute(SubCategory entity) {
		Optional<Category> optionalCategory = categoryRepository.findById(entity.getCategory().getId());
		
		if(!optionalCategory.isPresent()) {
			return "Categoria inexistente";
		}
		entity.setCategory(optionalCategory.get());
		return "";
	}

}
