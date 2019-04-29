package br.com.randomthings.services.change;

import java.util.List;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusChange;
import br.com.randomthings.services.IService;

public interface ChangeService extends IService<Change>{
	public List<Change> getByStatus(StatusChange status);
	public List<Change> getByClient(Client client);
}
