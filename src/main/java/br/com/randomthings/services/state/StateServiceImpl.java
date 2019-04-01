package br.com.randomthings.services.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.State;
import br.com.randomthings.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {
	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public List<State> findAll() {
		return stateRepository.findAll();
	}

}
