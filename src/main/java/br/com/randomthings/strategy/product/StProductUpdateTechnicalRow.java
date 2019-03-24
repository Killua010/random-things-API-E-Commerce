package br.com.randomthings.strategy.product;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.repository.ProductRepository;
import br.com.randomthings.repository.TechnicalRowRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StProductUpdateTechnicalRow implements IStrategy<Product> {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private TechnicalRowRepository rowRepository;
	
	@Override
	public String execute(Product entity) {
		Optional<Product> optionalProduct = productRepository.findByName(entity.getName());
		
		if(optionalProduct.isPresent()) {
			rowRepository.deleteByProduct(optionalProduct.get());
		}
		entity.setTechnicalRows((Set<TechnicalRow>) rowRepository.saveAll(entity.getTechnicalRows()));
		return "";
	}

}
