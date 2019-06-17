package br.com.randomthings.controller.generic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.ProviderDTO;
import br.com.randomthings.services.provider.web.ProviderServiceWeb;

@CrossOrigin
@RestController
@RequestMapping("/providers")
public class ProviderController extends AbstractController<ProviderDTO> {

	@Autowired
	private ProviderServiceWeb providerServiceWeb;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/findByName/{name}", method=RequestMethod.GET)
	public ResponseEntity<ProviderDTO> find(@PathVariable String name) {
		return ResponseEntity.ok()
				.body(providerServiceWeb.findByName(name));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value="/findByCategory/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<ProviderDTO>> findByCategory(@PathVariable Long id) {
		return ResponseEntity.ok()
				.body(providerServiceWeb.findByCategory(id));
	}
	
}
