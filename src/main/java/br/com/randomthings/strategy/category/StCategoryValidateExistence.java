package br.com.randomthings.strategy.category;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.repository.CategoryRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StCategoryValidateExistence implements IStrategy<Category> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public String execute(Category entity) {
		Optional<Category> optionalCategory = categoryRepository.findByName(entity.getName());
		
		if(optionalCategory.isPresent()) {
			if(!optionalCategory.get().getId().equals(entity.getId())) {
				return "Categoria já cadastrada no sistema";
			}
		}
		return "";
	}



}
