package br.com.randomthings.services.stock;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.StockInput;
import br.com.randomthings.repository.StockInputRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class StockInputServiceImpl extends AbstractService<StockInput, Long> implements StockInputService {
	
	public StockInputServiceImpl(StockInputRepository dao) {
		super(dao);
	}
}
