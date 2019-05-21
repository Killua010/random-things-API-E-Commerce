package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.TechnicalField;

@Repository
public interface CategoryRepository extends IRepository<Category, Long>, JpaRepository<Category, Long> {
	
	@Transactional(readOnly=true)
	Optional<Category> findByName(String name);

}
