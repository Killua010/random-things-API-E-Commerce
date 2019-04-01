package br.com.randomthings.strategy.credit_card;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.CreditCardFlag;
import br.com.randomthings.repository.CreditCardFlagRepository;
import br.com.randomthings.strategy.IStrategy;

@Service
public class StCreditCardRegisterHelp implements IStrategy<CreditCard> {
	
	@Autowired
	private CreditCardFlagRepository creditCardFlagRepository;

	@Override
	public String execute(CreditCard entity) {
//		Optional<CreditCardFlag> flagOptional = creditCardFlagRepository.findByIdAndStatusTrue(entity.getFlag().getId());
//		if(!flagOptional.isPresent()) {
//			return "A bandeira com id = " + entity.getFlag().getId() + " é inexistente";
//		}
//		
//		entity.setFlag(flagOptional.get());
		
		return "";
	}

}
