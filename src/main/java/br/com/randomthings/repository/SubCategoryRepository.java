package br.com.randomthings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;

@Repository
public interface SubCategoryRepository extends IRepository<SubCategory, Long>, JpaRepository<SubCategory, Long>{

	@Transactional(readOnly=true)
	Optional<SubCategory> findByNameAndCategory(String name, Category category);

}
