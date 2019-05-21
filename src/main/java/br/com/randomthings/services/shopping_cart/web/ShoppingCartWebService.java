package br.com.randomthings.services.shopping_cart.web;

import br.com.randomthings.dto.ProductDTO;
import br.com.randomthings.dto.ShoppingCartDTO;

public interface ShoppingCartWebService {
	
	public ShoppingCartDTO findById(Long id);
	
	public ShoppingCartDTO removeProduct(ProductDTO helper, Long clientId);
	
	public ShoppingCartDTO insertProduct(ProductDTO helper, Long clientId);
	
	public ShoppingCartDTO getShoppingCartByClientId(Long clientId);
	
	public ShoppingCartDTO updateShoppingCart(ShoppingCartDTO cartDTO);
	
	public void cleanShoppingCart(Long clientId);
}
