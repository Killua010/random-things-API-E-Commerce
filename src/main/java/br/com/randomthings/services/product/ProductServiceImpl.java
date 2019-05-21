package br.com.randomthings.services.product;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Image;
import br.com.randomthings.domain.Product;
import br.com.randomthings.domain.SubCategory;
import br.com.randomthings.domain.TechnicalRow;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ProductRepository;
import br.com.randomthings.services.AbstractService;
import br.com.randomthings.services.image.ImageService;
import br.com.randomthings.services.technical_row.TechnicalRowService;

@Service
public class ProductServiceImpl extends AbstractService<Product, Long> implements ProductService {
	private final ImageService imageService;

	private final TechnicalRowService technicalRowService;

	private final ProductRepository productRepository;
	
	@Autowired	
	public ProductServiceImpl(ProductRepository dao, TechnicalRowService technicalRowService,
			ImageService imageService) {
		super(dao);
		this.imageService = imageService;
		this.productRepository = dao;
		this.technicalRowService = technicalRowService;
	}

	@Override
	@Transactional 
	public Product save(Product domain) {
		for(TechnicalRow row: domain.getTechnicalRows()) {
			row.setId(null);
			technicalRowService.save(row);
		}
		
		try {
			Set<Image> images = new HashSet<>();
			for(Image image: domain.getImagens()) {
				image = imageService.save(image.getFile(), "");
				images.add(image);
			}
			domain.getImagens().clear();
			domain.getImagens().addAll(images);
		} catch (IOException e) {
			e.printStackTrace();
		}
		domain = productRepository.save(domain);
		return domain;
	}

	@Override
	@Transactional 
	public void deleteById(Long id) {
		Product product = findById(id);
		product.setStatus(false);
		product.setLastUpdate(LocalDateTime.now());
		productRepository.save(product);
	}

	@Override
	public Page<Product> getPageabled(Integer pageNumber, Integer qtdPage,String direction, String orderBy) {
		
		PageRequest pageRequest = PageRequest.of(pageNumber, qtdPage, Direction.valueOf(direction), orderBy);
		
		return productRepository.findAll(pageRequest);
	}

	@Override
	public Product update(Product product) {
		try {
			for(TechnicalRow row: technicalRowService.findByProduct(product)) {
				technicalRowService.deleteById(row.getId());
			}
			
			Set<Image> images = new HashSet<>();
			for(Image image: product.getImagens()) {
				image = imageService.save(image.getFile(), "");
				images.add(image);
			}
	
			product.getImagens().addAll(images);
			Product dbProduct = findById(product.getId());
			product.setImagens(dbProduct.getImagens());		
			
			return productRepository.save(product);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public Page<Product> getPageabledByCategory(Integer pageNumber, Integer qtdPage, String direction, String orderBy,
			SubCategory subCategory) {
		
		PageRequest pageRequest = PageRequest.of(pageNumber, qtdPage, Direction.valueOf(direction), orderBy);
		
		return productRepository.findAllBySubCategoryIn(subCategory, pageRequest);
	}

	@Override
	public List<Product> findBy(String param) {
		return productRepository.findBy(param);
	}
	
}
