package br.com.randomthings.strategy.product;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Product;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StProductRequiredData implements IStrategy<Product> {

	@Override
	public String execute(Product entity) {
		StringBuilder err = new StringBuilder();
		if(entity.getSubCategory().size() == 0) {
			err.append("O produto necessida de no minimo uma subcategoria");
		}
		if(entity.getTechnicalRows().size() == 0) {
			err.append("O produto necessida de no minimo um campo de descrição");
		}
		return err.toString();
	}

}
