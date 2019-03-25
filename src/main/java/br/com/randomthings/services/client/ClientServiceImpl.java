package br.com.randomthings.services.client;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Client;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.repository.ContactRepository;
import br.com.randomthings.repository.UserRepository;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Client findById(Long id) {		
		return clientRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Client.class.getSimpleName()));
	}

	@Override
	@Transactional 
	public Client save(Client domain) {
		contactRepository.save(domain.getContact());
		userRepository.save(domain.getUser());
		domain = clientRepository.save(domain);
		return domain;
	}

	@Override
	@Transactional 
	public void deletar(Long id) {
		Client client = findById(id);
		client.setStatus(false);
		client.setLastUpdate(LocalDateTime.now());
		clientRepository.save(client);
	}
	
}
