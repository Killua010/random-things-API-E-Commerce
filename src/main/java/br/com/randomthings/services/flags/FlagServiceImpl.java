package br.com.randomthings.services.flags;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CreditCardFlag;
import br.com.randomthings.domain.State;
import br.com.randomthings.repository.CreditCardFlagRepository;
import br.com.randomthings.repository.StateRepository;

@Service
public class FlagServiceImpl implements FlagService {
	
	@Autowired
	private CreditCardFlagRepository creditCardFlagRepository;

	@Override
	public List<CreditCardFlag> findAll() {
		return creditCardFlagRepository.findAll();
	}

}
