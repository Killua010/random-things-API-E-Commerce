package br.com.randomthings.services.cart_item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.randomthings.domain.ShoppingCartItem;
import br.com.randomthings.repository.ShoppingCartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService{
	
	@Autowired
	private ShoppingCartItemRepository cartItemRepository;

	@Override
	public void delete(ShoppingCartItem cartItem) {
		cartItemRepository.deleteById(cartItem.getId());
		cartItemRepository.delete(cartItem);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}
