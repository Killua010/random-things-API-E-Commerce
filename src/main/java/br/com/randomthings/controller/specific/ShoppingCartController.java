package br.com.randomthings.controller.specific;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.randomthings.dto.ProductDTO;
import br.com.randomthings.dto.ShoppingCartDTO;
import br.com.randomthings.services.shopping_cart.web.ShoppingCartWebService;

@CrossOrigin
@RestController
@RequestMapping("/shoppingCarts")
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartWebService shoppingCartWeb;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ShoppingCartDTO> find(@PathVariable Long id ) {
		return ResponseEntity.ok().body(shoppingCartWeb.findById(id));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<ShoppingCartDTO> update(@PathVariable Long id, @RequestBody ShoppingCartDTO cartDTO) {
		cartDTO.setId(id);
		return ResponseEntity.ok(shoppingCartWeb.updateShoppingCart(cartDTO));
	}
		
	@RequestMapping(value="/client/{idClient}", method=RequestMethod.POST)
	public ResponseEntity<ShoppingCartDTO> insertShoppingCart(@PathVariable("idClient") Long clientId, @RequestBody ProductDTO product) {
		return ResponseEntity.ok(shoppingCartWeb.insertProduct(product, clientId));
	}
	
	@RequestMapping(value="/client/{idClient}", method=RequestMethod.DELETE)
	public ResponseEntity<ShoppingCartDTO> removeShoppingCart(@PathVariable("idClient") Long clientId, @RequestBody ProductDTO product) {
		return ResponseEntity.ok(shoppingCartWeb.removeProduct(product, clientId));
	}
	
	@RequestMapping(value="/client/{idClient}", method=RequestMethod.GET)
	public ResponseEntity<ShoppingCartDTO> getShoppingCart(@PathVariable("idClient") Long clientId) {
		return ResponseEntity.ok(shoppingCartWeb.getShoppingCartByClientId(clientId));
	}
}
