package br.com.randomthings.services.shopping_cart.web;

import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.viewhelper.ProductViewHelper;

public interface ShoppingCartWeb {
	
	public ShoppingCart removeProduct(ProductViewHelper helper, Long clientId);
	
	public ShoppingCart insertProduct(ProductViewHelper helper, Long clientId);
	
	public ShoppingCart getShoppingCartByClientId(Long clientId);
	
	public void cleanShoppingCart(Long clientId);
}
