package br.com.randomthings.services.technical_row;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.repository.TechnicalRowRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class TechnicalRowServiceImpl extends AbstractService<TechnicalRow, Long> implements TechnicalRowService {
	@Autowired
	private TechnicalRowRepository technicalRowRepository;
	
	public TechnicalRowServiceImpl(TechnicalRowRepository dao) {
		super(dao);
	}

	@Override
	public List<TechnicalRow> findByProduct(Product product) {
		return technicalRowRepository.findAllByProduct(product);
	}

}
