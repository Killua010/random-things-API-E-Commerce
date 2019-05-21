package br.com.randomthings.services.sub_category;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.repository.SubCategoryRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class SubCategoryServiceImpl extends AbstractService<SubCategory, Long> implements SubCategoryService {
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	public SubCategoryServiceImpl(SubCategoryRepository dao) {
		super(dao);
	}
	
	@Override
	@Transactional 
	public void deleteById(Long id) {
		SubCategory subCategory = findById(id);
		subCategory.setStatus(false);
		subCategory.setLastUpdate(LocalDateTime.now());
		subCategoryRepository.save(subCategory);
	}
	
}
