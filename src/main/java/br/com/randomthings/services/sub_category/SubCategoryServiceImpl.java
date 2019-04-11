package br.com.randomthings.services.sub_category;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ProductRepository;
import br.com.randomthings.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	public SubCategory findById(Long id) {		
		return subCategoryRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + SubCategory.class.getSimpleName()));
	}

	@Override
	@Transactional 
	public SubCategory save(SubCategory domain) {
		domain = subCategoryRepository.save(domain);
		return domain;
	}

	@Override
	@Transactional 
	public void delete(Long id) {
		SubCategory subCategory = findById(id);
		for(Product product: subCategory.getProducts()) {
			product.getSubCategory().remove(subCategory);
			productRepository.save(product);
		}
		subCategoryRepository.delete(subCategory);
	}

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll(); 
	}
	
}
