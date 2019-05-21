package br.com.randomthings.services.change;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusChange;
import br.com.randomthings.repository.ChangeRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class ChangeServiceImpl extends AbstractService<Change, Long> implements ChangeService{
	
	@Autowired
	private ChangeRepository changeRepository;
	
	public ChangeServiceImpl(ChangeRepository dao) {
		super(dao);
	}
	
	@Override
	public List<Change> getByStatus(StatusChange status) {
		return changeRepository.findAllByStatusChange(status);
	}

	@Override
	public List<Change> getByClient(Client client) {
		return changeRepository.findAllByClient(client);
	}

	@Override
	public List<Change> getByOrder(Order order) {
		return changeRepository.findAllByOrder(order);
	}

}
