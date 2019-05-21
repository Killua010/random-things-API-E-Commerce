package br.com.randomthings.controller.generic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.User;
import br.com.randomthings.dto.ClientDTO;
import br.com.randomthings.services.client.ClientService;

@CrossOrigin
@RestController
@RequestMapping("/clients")
public class ClientController extends AbstractController<ClientDTO>{
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public ResponseEntity<ClientDTO> login(@RequestBody User user){
		Client client = clientService.findByUser(user);
		return ResponseEntity.ok()
				.body((ClientDTO)new ClientDTO().from(client));
	}
	
}
