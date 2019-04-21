package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.domain.ShoppingCartItem;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class ShoppingCartDTO extends EntityDTO {
	
	private Float subTotal;
	
	private Integer quantityProduct;
	
	private List<CartItemDTO> itens;
	
	private void setItens(Set<ShoppingCartItem> cardItens) {
		this.itens = new ArrayList<>();
		for(ShoppingCartItem item: cardItens) {
			this.itens.add(CartItemDTO.from(item));
		}
	}
		
	public static ShoppingCartDTO from(ShoppingCart shoppingCart) {
		ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
	
		shoppingCartDTO.setQuantityProduct(shoppingCart.getCartItems().size());
		shoppingCart.setSubTotal(shoppingCart.getSubTotal());
		shoppingCartDTO.setId(shoppingCart.getId());
		shoppingCartDTO.setStatus(shoppingCart.getStatus());
		shoppingCartDTO.setCreationDate(shoppingCart.getCreationDate());
		shoppingCartDTO.setLastUpdate(shoppingCart.getLastUpdate());
		shoppingCartDTO.setItens(shoppingCart.getCartItems());
		
		return shoppingCartDTO;
	}
}
