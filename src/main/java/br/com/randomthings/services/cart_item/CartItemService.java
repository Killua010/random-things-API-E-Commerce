package br.com.randomthings.services.cart_item;

import br.com.randomthings.domain.ShoppingCartItem;

public interface CartItemService {
	public void delete(Long id);
	public void delete(ShoppingCartItem cartItem);
}
