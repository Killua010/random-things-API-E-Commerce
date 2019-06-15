package br.com.randomthings.services.inactive;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Inactivation;
import br.com.randomthings.repository.InactivationRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class InactivationServiceImpl extends AbstractService<Inactivation, Long> implements InactivationService{

	public InactivationServiceImpl(InactivationRepository dao) {
		super(dao);
	}

}
