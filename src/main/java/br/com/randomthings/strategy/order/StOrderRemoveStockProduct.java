package br.com.randomthings.strategy.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.OrderItem;
import br.com.randomthings.domain.Product;
import br.com.randomthings.services.product.ProductService;
import br.com.randomthings.services.stock.StockService;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StOrderRemoveStockProduct implements IStrategy<Order> {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockService stockService;
	
	@Override
	public String execute(Order entity) {
		for(OrderItem item: entity.getItems()) {
			Product product = productService.findById(item.getProduct().getId());
			if(item.getQuantity() > product.getStock().getTotalQuantity()) {
				return "Produto com o id: " + product.getId() + " - não tem estoque suficiente.";
			}
		}
		for(OrderItem item: entity.getItems()) {
			Product product = productService.findById(item.getProduct().getId());
			Integer total = product.getStock().getTotalQuantity();
			System.err.println(item.getQuantity());
			System.err.println(total);
			total =  total - item.getQuantity();
			product.getStock().setTotalQuantity(total);
//			stockService.save(product.getStock());
			System.out.println(product.getStock().getTotalQuantity());
		}
		return "";
	}

}
