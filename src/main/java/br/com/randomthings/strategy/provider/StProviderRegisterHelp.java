package br.com.randomthings.strategy.provider;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.repository.CategoryRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StProviderRegisterHelp implements IStrategy<Provider> {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public String execute(Provider entity) {
		
		Optional<Category> optionalCategory = categoryRepository.findById(entity.getCategory().getId());
		
		if(!optionalCategory.isPresent()) {
			return "A categoria com id = " + entity.getCategory().getId() + " é inexistente";
		}
		
		entity.setCategory(optionalCategory.get());
		
		
		return "";
	}

}
