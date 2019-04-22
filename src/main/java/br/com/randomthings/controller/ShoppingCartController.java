package br.com.randomthings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.domain.Client;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.dto.ClientDTO;
import br.com.randomthings.dto.ShoppingCartDTO;
import br.com.randomthings.services.shopping_cart.ShoppingCartService;
import br.com.randomthings.services.shopping_cart.web.ShoppingCartWebService;
import br.com.randomthings.viewhelper.ProductViewHelper;

@CrossOrigin
@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartWebService shoppingCartWeb;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ShoppingCartDTO> find(@PathVariable Long id ) {
		ShoppingCart shoppingCart = shoppingCartService.findById(id);
		return ResponseEntity.ok()
				.body(ShoppingCartDTO.from(shoppingCart));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ShoppingCartDTO> update(@PathVariable Long id, @RequestBody ShoppingCartDTO cartDTO) {
		cartDTO.setId(id);
		return ResponseEntity.ok(ShoppingCartDTO.from(
				shoppingCartWeb.updateShoppingCart(cartDTO)));
	}
		
	@RequestMapping(value="/client/{idClient}", method=RequestMethod.POST)
	public ResponseEntity<ShoppingCartDTO> insertShoppingCart(@PathVariable("idClient") Long clientId, @RequestBody ProductViewHelper product) {
		return ResponseEntity.ok(ShoppingCartDTO.from(
				shoppingCartWeb.insertProduct(product, clientId)));
	}
	
	@RequestMapping(value="/client/{idClient}", method=RequestMethod.DELETE)
	public ResponseEntity<ShoppingCartDTO> removeShoppingCart(@PathVariable("idClient") Long clientId, @RequestBody ProductViewHelper product) {
		return ResponseEntity.ok(ShoppingCartDTO.from(
				shoppingCartWeb.removeProduct(product, clientId)));
	}
	
	@RequestMapping(value="/client/{idClient}", method=RequestMethod.GET)
	public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable("idClient") Long clientId) {
		return ResponseEntity.ok(ShoppingCartDTO.from(
				shoppingCartWeb.getShoppingCartByClientId(clientId)));
	}
}
