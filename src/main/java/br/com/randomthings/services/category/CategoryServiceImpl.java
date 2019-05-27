package br.com.randomthings.services.category;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Image;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.repository.CategoryRepository;
import br.com.randomthings.services.AbstractService;
import br.com.randomthings.services.image.ImageService;
import br.com.randomthings.services.sub_category.SubCategoryService;

@Service
public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {

	private final ImageService imageService;
	
	private final SubCategoryService subCategoryService;
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository dao, ImageService imageService, CategoryRepository categoryRepository,
			SubCategoryService subCategoryService) {
		super(dao);
		this.imageService = imageService;
		this.categoryRepository = categoryRepository;
		this.subCategoryService = subCategoryService;
	}
	
	@Override
	@Transactional 
	public Category save(Category domain) {
		try {
			Image image = imageService.save(domain.getImage().getFile(), "");
			domain.setImage(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
		domain = categoryRepository.save(domain);
		return domain;
	}
	
	@Override
	@Transactional 
	public Category update(Category domain) {
		if(domain.getImage().getFile() != null) {
			try {
				Image image = imageService.save(domain.getImage().getFile(), "");
				domain.setImage(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		domain = categoryRepository.save(domain);
		return domain;
	}
	
	@Override
	@Transactional 
	public void deleteById(Long id) {
		Category category = findById(id);
		category.setStatus(false);
		category.setLastUpdate(LocalDateTime.now());
		
		for(SubCategory subCategory: category.getSubCategories()) {
			subCategory.setStatus(false);
			subCategory.setLastUpdate(LocalDateTime.now());
			subCategoryService.save(subCategory);
		}
		
		categoryRepository.save(category);
	}


}
