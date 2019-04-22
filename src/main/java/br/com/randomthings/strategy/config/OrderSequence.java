package br.com.randomthings.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.randomthings.domain.Order;
import br.com.randomthings.strategy.Sequence;
import br.com.randomthings.strategy.order.StOrderRemoveStockProduct;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Configuration
public class OrderSequence {
	
	@Autowired
	StOrderRemoveStockProduct stOrderRemoveStockProduct;
	
	@Autowired
	StRegistration stRegistration;
	
	@Autowired
	StLastUpdate stLastUpdate;

	@Bean("SAVE_ORDER")
	public Sequence<Order> saveCategory() {
		return new Sequence<Order>()
				.add(stOrderRemoveStockProduct)
				.add(stRegistration);
	}
//	
//	@Bean("UPDATE_CATEGORY")
//	public Sequence<Category> updateCategory() {
//		return new Sequence<Category>()
//				.add(stCategoryValidateExistence)
//				.add(stLastUpdate);
//	}

}
