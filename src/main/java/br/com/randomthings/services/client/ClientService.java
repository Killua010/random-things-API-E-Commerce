package br.com.randomthings.services.client;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.User;
import br.com.randomthings.services.IService;

public interface ClientService extends IService<Client> {
	Client findByUser(User user);
}
