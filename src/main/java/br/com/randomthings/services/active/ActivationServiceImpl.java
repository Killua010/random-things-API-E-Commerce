package br.com.randomthings.services.active;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Activation;
import br.com.randomthings.repository.ActivationRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class ActivationServiceImpl extends AbstractService<Activation, Long> implements ActivationService{

	public ActivationServiceImpl(ActivationRepository dao) {
		super(dao);
	}

}
