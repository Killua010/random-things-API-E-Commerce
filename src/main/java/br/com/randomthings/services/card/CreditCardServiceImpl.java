package br.com.randomthings.services.card;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.CreditCardFlag;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.CreditCardRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;
	

	@Override
	public CreditCard findById(Long id) {
		return creditCardRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + CreditCard.class.getSimpleName()));
	}

	@Override
	public CreditCard save(CreditCard domain) {
//		domain.getFlag().setId((long) 1);
		domain = creditCardRepository.saveAndFlush(domain);
		return domain;
	}

	@Override
	public void delete(Long id) {
		findById(id);
		creditCardRepository.deleteById(id);
	}

	@Override
	public List<CreditCard> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
