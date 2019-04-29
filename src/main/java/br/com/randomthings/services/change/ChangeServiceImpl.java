package br.com.randomthings.services.change;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Change;
import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.Order;
import br.com.randomthings.domain.StatusChange;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ChangeRepository;

@Service
public class ChangeServiceImpl implements ChangeService{
	
	@Autowired
	private ChangeRepository changeRepository;

	@Override
	public Change findById(Long id) {
		return changeRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Change.class.getSimpleName()));
	}

	@Override
	public Change save(Change domain) {
		return changeRepository.save(domain);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Change> findAll() {
		// TODO Auto-generated method stub
		return null;
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
