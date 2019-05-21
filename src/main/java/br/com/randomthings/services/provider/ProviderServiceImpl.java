package br.com.randomthings.services.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.Provider;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ProviderRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class ProviderServiceImpl extends AbstractService<Provider, Long> implements ProviderService {
	@Autowired
	private ProviderRepository providerRepository;
	
	public ProviderServiceImpl(ProviderRepository dao) {
		super(dao);
	}
	
	@Override
	public Provider findByName(String name) {
		return providerRepository.findByName(name).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! nome: " + name
				+ ", tipo: " + Provider.class.getSimpleName()));
	}
	
}
