package br.com.randomthings.services.shopping_cart.web;

import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.dto.ShoppingCartDTO;
import br.com.randomthings.viewhelper.ProductViewHelper;

public interface ShoppingCartWebService {
	
	public ShoppingCart removeProduct(ProductViewHelper helper, Long clientId);
	
	public ShoppingCart insertProduct(ProductViewHelper helper, Long clientId);
	
	public ShoppingCart getShoppingCartByClientId(Long clientId);
	
	public ShoppingCart updateShoppingCart(ShoppingCartDTO cartDTO);
	
	public void cleanShoppingCart(Long clientId);
}
