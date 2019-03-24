package br.com.randomthings.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client findById(Long id) {		
		return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Client.class.getSimpleName()));
	}
}
