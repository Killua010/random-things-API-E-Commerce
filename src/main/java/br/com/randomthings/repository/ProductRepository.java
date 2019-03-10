package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.Product;

@Repository
public interface ProductRepository extends IRepository<Product>{
	
	@Transactional(readOnly=true)
	Optional<Product> findByName(String name);

}
