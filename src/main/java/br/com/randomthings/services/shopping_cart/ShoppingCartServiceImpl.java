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
import br.com.randomthings.services.client.ClientService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Override
	public ShoppingCart findById(Long id) {
		return shoppingCartRepository.findByIdAndStatusTrue(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + ShoppingCart.class.getSimpleName()));
	}

	@Override
	@Transactional
	public ShoppingCart save(ShoppingCart domain) {
		ShoppingCart cart = shoppingCartRepository.save(domain);
		clientRepository.save(domain.getClient());
		return cart;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShoppingCart> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
