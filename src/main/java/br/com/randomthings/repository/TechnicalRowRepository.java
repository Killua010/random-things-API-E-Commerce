package br.com.randomthings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.TechnicalField;
import br.com.randomthings.domain.TechnicalRow;

@Repository
public interface TechnicalRowRepository extends IRepository<TechnicalRow> {
	
	@Query("DELETE FROM _technical_row row WHERE row.product = ?1")
	void deleteByProduct(Product product);
	
	List<TechnicalRow> findAllByField(TechnicalField field);

}
