package br.com.randomthings.services.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.State;
import br.com.randomthings.repository.StateRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class StateServiceImpl extends AbstractService<State, Long> implements StateService {

	@Autowired
	private StateRepository stateRepository;
	
	public StateServiceImpl(StateRepository dao) {
		super(dao);
	}

}
