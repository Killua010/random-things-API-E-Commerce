package br.com.randomthings.services.category;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Image;
import br.com.randomthings.repository.CategoryRepository;
import br.com.randomthings.services.AbstractService;
import br.com.randomthings.services.image.ImageService;

@Service
public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {

	private final ImageService imageService;
	
	private final CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository dao, ImageService imageService, CategoryRepository categoryRepository) {
		super(dao);
		this.imageService = imageService;
		this.categoryRepository = categoryRepository;				
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

}
