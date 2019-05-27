package br.com.randomthings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;

@Repository
public interface ProductRepository extends IRepository<Product, Long>, JpaRepository<Product, Long> {
	
	@Transactional(readOnly=true)
	Optional<Product> findByName(String name);
	
	@Transactional(readOnly=true)
	Page<Product> findAllBySubCategoryIn(SubCategory subCategory, Pageable pageRequest);
	
	
	 @Query("SELECT prod FROM _product prod JOIN prod.subCategory subCat  WHERE TRUE = prod.status AND "
		 + "(UPPER(prod.name) LIKE UPPER(CONCAT('%',:param,'%')) OR UPPER(subCat.name) LIKE UPPER(CONCAT('%',:param,'%'))"
		 + " OR UPPER(subCat.category.name) LIKE UPPER(CONCAT('%',:param,'%')) " +
		 "OR prod.price LIKE CONCAT('%',:param,'%'))")
	List<Product> findBy(@Param("param") String param);
//	@Query("SELECT prod FROM _product prod WHERE (UPPER(prod.name) LIKE UPPER(CONCAT('%',:param,'%')))")

	@Query("SELECT prod FROM _product prod JOIN prod.subCategory subCat WHERE subCat.category = ?1")
	List<Product> findByCategory(Category category);
}
