package br.com.randomthings.controller.specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.domain.Image;
import br.com.randomthings.services.image.ImageService;
import br.com.randomthings.storage.LocalStorage;

@CrossOrigin
@RestController
@RequestMapping("/images")
public class ImageController {
	
	@Autowired
	private LocalStorage localStorage;
	
	@Autowired
	private ImageService imageService;
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> image(@PathVariable("id") Long id) {
		
		Image image = this.imageService.findById(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		headers.setContentType(MediaType.parseMediaType(image.getType()));
		headers.setContentLength(image.getSize());
		byte[] bytes = localStorage.loadBytes(image.getName());
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers,
				HttpStatus.OK);
		return responseEntity;
	}
}
