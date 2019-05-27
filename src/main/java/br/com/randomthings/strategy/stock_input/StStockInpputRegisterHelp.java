package br.com.randomthings.strategy.stock_input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.Provider;
import br.com.randomthings.domain.StockInput;
import br.com.randomthings.services.product.ProductService;
import br.com.randomthings.services.provider.ProviderService;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StStockInpputRegisterHelp implements IStrategy<StockInput> {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProviderService providerService;
	
	@Override
	public String execute(StockInput entity) {
		
		Product product = productService.findById(entity.getProduct().getId());
		
		Provider provider = providerService.findById(entity.getProvider().getId());
		
		entity.setProduct(product);
		entity.setProvider(provider);
		
		return "";
	}

}
