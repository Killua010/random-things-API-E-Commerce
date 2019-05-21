package br.com.randomthings.services.shopping_cart;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.exception.ObjectNotFoundException;
import br.com.randomthings.repository.ClientRepository;
import br.com.randomthings.repository.ShoppingCartRepository;
import br.com.randomthings.services.AbstractService;
import br.com.randomthings.services.client.ClientService;

@Service
public class ShoppingCartServiceImpl extends AbstractService<ShoppingCart, Long> implements ShoppingCartService {
	private final ClientRepository clientRepository;
	
	private final ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	public ShoppingCartServiceImpl(ShoppingCartRepository dao, ClientRepository clientRepository) {
		super(dao);
		this.clientRepository = clientRepository;
		this.shoppingCartRepository = dao;
	}

	@Override
	@Transactional
	public ShoppingCart save(ShoppingCart domain) {
		ShoppingCart cart = shoppingCartRepository.save(domain);
		clientRepository.save(domain.getClient());
		return cart;
	}
}
