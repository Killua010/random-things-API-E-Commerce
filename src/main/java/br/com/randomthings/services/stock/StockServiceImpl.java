package br.com.randomthings.services.stock;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Stock;
import br.com.randomthings.repository.StockRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class StockServiceImpl extends AbstractService<Stock, Long> implements StockService {
	
	public StockServiceImpl(StockRepository dao) {
		super(dao);
	}
}
