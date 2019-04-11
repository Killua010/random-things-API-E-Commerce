package br.com.randomthings.services.image;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.Image;
import br.com.randomthings.services.IService;

public interface ImageService {
	Image save(MultipartFile file, String description) throws IOException;

	Image findById(Long id);
}
