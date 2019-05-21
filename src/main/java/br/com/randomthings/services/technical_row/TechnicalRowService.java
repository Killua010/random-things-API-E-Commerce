package br.com.randomthings.services.technical_row;
	
import java.util.List;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.services.IService;

public interface TechnicalRowService extends IService<TechnicalRow, Long> {
	
	List<TechnicalRow> findByProduct(Product product);
	
}
