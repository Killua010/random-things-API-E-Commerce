package br.com.randomthings.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.ShoppingCart;
import br.com.randomthings.domain.ShoppingCartItem;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Component
public class ShoppingCartDTO extends AbstractDTO<ShoppingCart> {
	
	private Float subTotal;
	
	private Integer quantityProduct;
	
	@JsonProperty(access = Access.READ_ONLY)
	private List<CartItemDTO> itens;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long[] idItem;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Integer[] quantityItem;
	
	@Override
	public IDTO from(ShoppingCart shoppingCart) {
		ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
		this.from(shoppingCart, shoppingCartDTO);
		
		shoppingCartDTO.setQuantityProduct(shoppingCart.getCartItems().size());
		shoppingCart.setSubTotal(shoppingCart.getSubTotal());
		shoppingCartDTO.setItens(shoppingCart.getCartItems());
		
		return shoppingCartDTO;
	}

	@Override
	public ShoppingCart fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
	private void setItens(Set<ShoppingCartItem> cardItens) {
		this.itens = new ArrayList<>();
		for(ShoppingCartItem item: cardItens) {
			this.itens.add((CartItemDTO) new CartItemDTO().from(item));
		}
	}
		
	
}
