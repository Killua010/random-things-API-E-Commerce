package br.com.randomthings.services.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Image;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.exception.UnsupportedFile;
import br.com.randomthings.repository.ImageRepository;
import br.com.randomthings.storage.LocalStorage;
import br.com.randomthings.storage.SimpleImageInfo;

@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private LocalStorage localStorage;

	@Override
	public Image save(MultipartFile file, String description) throws IOException {

		SimpleImageInfo nomeTemp = new SimpleImageInfo(file.getBytes());
		if(nomeTemp.getHeight()>2000 || nomeTemp.getWidth()>2000) {
			throw new UnsupportedFile();
		}
		
		Image image = new Image();
		image.setOriginName(file.getOriginalFilename());
		image.setType(file.getContentType());
		image.setSize(file.getSize());
		image.setDescription(description);
		image.setStatus(true);
		image.setCreationDate(LocalDateTime.now());
		image.setLastUpdate(LocalDateTime.now());
		InputStream input = new ByteArrayInputStream(file.getBytes());
		BufferedImage buffer = ImageIO.read(input);
		image.setHeigth(buffer.getHeight());
		image.setWidth(buffer.getWidth());
		
		image = imageRepository.save(image);
		
		String extension = image.getOriginName().substring(image.getOriginName().lastIndexOf("."));
		String fileName = "imagem_" + image.getId() + extension;

		localStorage.store(file, fileName);

		image.setName(fileName);
		imageRepository.save(image);

		return image;
	}

	@Override
	public Image findById(Long id) {
		return imageRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Client.class.getSimpleName()));
	}


}
