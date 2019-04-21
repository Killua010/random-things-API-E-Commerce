package br.com.randomthings.services.category;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Category;
import br.com.randomthings.domain.Image;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.CategoryRepository;
import br.com.randomthings.services.image.ImageService;
import br.com.randomthings.strategy.standard.StLastUpdate;
import br.com.randomthings.strategy.standard.StRegistration;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category findById(Long id) {		
		return categoryRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Category.class.getSimpleName()));
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
	public void delete(Long id) {
		categoryRepository.deleteById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll(); 
	}
	
}
