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
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ProductRepository;
import br.com.randomthings.services.image.ImageService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product findById(Long id) {		
		return productRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Product.class.getSimpleName()));
	}

	@Override
	@Transactional 
	public Product save(Product domain) {
		System.err.println(domain.getImagens().size());
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
	public void delete(Long id) {
		Product product = findById(id);
		product.setStatus(false);
		product.setLastUpdate(LocalDateTime.now());
		productRepository.save(product);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll(); 
	}

	@Override
	public Page<Product> getPageabled(Integer pageNumber, Integer qtdPage,String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(pageNumber, qtdPage, Direction.valueOf(direction), orderBy);
		
		return productRepository.findAll(pageRequest);
	}
	
}
