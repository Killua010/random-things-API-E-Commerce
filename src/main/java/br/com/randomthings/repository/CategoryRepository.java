package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.Category;

@Repository
public interface CategoryRepository extends IRepository<Category>{
	
	@Transactional(readOnly=true)
	Optional<Category> findByName(String name);

}
