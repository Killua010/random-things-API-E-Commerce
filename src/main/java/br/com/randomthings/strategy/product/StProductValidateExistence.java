package br.com.randomthings.strategy.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Product;
import br.com.randomthings.repository.ProductRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StProductValidateExistence implements IStrategy<Product> {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public String execute(Product entity) {
		Optional<Product> optionalProduct = productRepository.findByName(entity.getName());
		
		if(optionalProduct.isPresent()) {
			if(!optionalProduct.get().getId().equals(entity.getId())) {
				return "Produto já cadastrada no sistema";
			}
		}
		return "";
	}

}
