package br.com.randomthings.services.card;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.Product;
import br.com.randomthings.repository.CreditCardRepository;
import br.com.randomthings.services.AbstractService;

@Service
public class CreditCardServiceImpl extends AbstractService<CreditCard, Long> implements CreditCardService {

	private final CreditCardRepository creditCardRepository;
	
	public CreditCardServiceImpl(CreditCardRepository dao) {
		super(dao);
		this.creditCardRepository =dao;
	}
	
	@Override
	@Transactional 
	public void deleteById(Long id) {
		CreditCard card = findById(id);
		card.setStatus(false);
		card.setLastUpdate(LocalDateTime.now());
		creditCardRepository.save(card);
	}
	
}
