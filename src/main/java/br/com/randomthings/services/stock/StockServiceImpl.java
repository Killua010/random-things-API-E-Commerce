package br.com.randomthings.services.stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Stock;
import br.com.randomthings.repository.StockRepository;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	StockRepository stockRepository;

	@Override
	public Stock findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stock save(Stock domain) {
		return stockRepository.save(domain);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stock> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
