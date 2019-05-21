package br.com.randomthings.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.randomthings.domain.CreditCard;
import br.com.randomthings.domain.DeliveryAddress;
import br.com.randomthings.domain.ShoppingCartItem;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@Component
public class CartItemDTO extends AbstractDTO<ShoppingCartItem> {
	
	private ProductDTO product;
	
	private Integer quantity;
	
	private Float subTotal;
		
	@Override
	public IDTO from(ShoppingCartItem cartItem) {
		CartItemDTO cartItemDTO = new CartItemDTO();
		this.from(cartItem, cartItemDTO);
		
		Float subTotal = cartItem.getProduct().getPrice() * cartItem.getQuantity();
		cartItemDTO.setSubTotal(subTotal);
		cartItemDTO.setQuantity(cartItem.getQuantity());
		
		ProductDTO helper = (ProductDTO) new ProductDTO().from(cartItem.getProduct());
		cartItemDTO.setProduct(helper);
		
		return cartItemDTO;
	}

	@Override
	public ShoppingCartItem fill(Long... params) {
		throw new UnsupportedOperationException("Em desenvolvimento.");
	}
	
}
