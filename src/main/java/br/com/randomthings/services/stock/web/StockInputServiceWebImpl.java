package br.com.randomthings.services.stock.web;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.Stock;
import br.com.randomthings.domain.StockInput;
import br.com.randomthings.dto.StockInputDTO;
import br.com.randomthings.exception.StrategyValidation;
import br.com.randomthings.services.ExecuteStrategys;
import br.com.randomthings.services.product.ProductService;
import br.com.randomthings.services.stock.StockInputService;
import br.com.randomthings.services.stock.StockService;

@Service
public class StockInputServiceWebImpl extends ExecuteStrategys<StockInput> implements StockInputServiceWeb {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private StockInputService stockInputService;
	
	@Override
	public StockInput save(StockInputDTO stockInputDTO) {
		StockInput stockInput = stockInputDTO.fill();
		
		StringBuilder errors = runStrategys(stockInput, "Save");
		
		if(errors.length() != 0) {
			throw new StrategyValidation(errors);
		}
		
		Stock stock = stockService.findById(stockInput.getProduct().getStock().getId());
		
		stock.setTotalQuantity(stockInput.getQuantity() + stock.getTotalQuantity());
		
		Product product = stockInput.getProduct();
		
		Float price = product.getPrice() - (product.getPrice() * (product.getPricingGroup().getProfitPercentage() / 100));
		
		Float newPrice = ((price + stockInput.getValue()) / 2) + ((price + stockInput.getValue()) / 2) * (product.getPricingGroup().getProfitPercentage() / 100);
		newPrice = (float) new BigDecimal(newPrice).setScale(2, BigDecimal.ROUND_FLOOR).doubleValue();
		
		product.setPrice(newPrice);
		product.setStatus(true);
		
		stockInput = stockInputService.save(stockInput);
		
		stock.getStockInputs().add(stockInput);
		
		productService.update(product);
		stockService.save(stock);
		
		return stockInput;
	}

	@Override
	public List<StockInputDTO> getAll() {
		List<StockInput> list = stockInputService.findAll();
		List<StockInputDTO> inputDTOs = new ArrayList<StockInputDTO>();
		System.err.println(list.size());
		for(StockInput input: list) {
			inputDTOs.add((StockInputDTO) new StockInputDTO().from(input));
		}
		System.out.println(inputDTOs.size());
		return inputDTOs;
	}

}
