package br.com.randomthings.strategy.inactivation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.domain.Inactivation;
import br.com.randomthings.domain.Product;
import br.com.randomthings.repository.ProductRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StInactivationValidateProduct implements IStrategy<Inactivation> {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public String execute(Inactivation entity) {
		Optional<Product> optionalProduct = productRepository.findById(entity.getProduct().getId());
		
		if(!optionalProduct.isPresent()) {
			return "Produto inexistente";
		}
		
		if(optionalProduct.get().getStatus() == false) {
			return "Produto já inativado";
		}
		
		optionalProduct.get().setStatus(false);
		entity.setProduct(optionalProduct.get());
		
		return "";
	}

}
