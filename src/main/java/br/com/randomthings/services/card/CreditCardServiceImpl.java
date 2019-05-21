package br.com.randomthings.services.card;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.repository.CreditCardRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class CreditCardServiceImpl extends AbstractService<CreditCard, Long> implements CreditCardService {

	public CreditCardServiceImpl(CreditCardRepository dao) {
		super(dao);
	}
	
}
