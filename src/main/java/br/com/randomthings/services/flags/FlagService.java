package br.com.randomthings.services.flags;

import java.util.List;

import br.com.randomthings.domain.CreditCardFlag;

public interface FlagService {

	List<CreditCardFlag> findAll();

}
